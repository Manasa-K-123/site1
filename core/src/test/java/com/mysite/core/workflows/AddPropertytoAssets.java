package com.mysite.core.workflows;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Add Property to Asset"}
)
public class AddPropertytoAssets implements WorkflowProcess {
    private static final Logger log = LoggerFactory.getLogger(AddPropertytoAssets.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        // Getting the payload path of the assets
        String payloadPath = workItem.getWorkflowData().getPayload().toString();
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "writeService");

        try (ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(param)) {
            log.info("Subservice has permission to access the resource.");

            // Get the resource for the payload path
            Resource assetResource = resourceResolver.getResource(payloadPath);
            if (assetResource == null) {
                log.error("Asset Resource not found at path: {}", payloadPath);
                return; // Exit if the resource is null
            }

            // Adapt resource to Node
            Node assetNode = assetResource.adaptTo(Node.class);
            if (assetNode == null) {
                log.error("Asset Node could not be adapted at path: {}", payloadPath);
                return; // Exit if the node is null
            }

            log.info("Asset node retrieved successfully: {}", assetNode.getPath());

            if (assetNode.hasNode("jcr:content")) {
                Node jcrContent = assetNode.getNode("jcr:content");
                log.info("jcr:content node retrieved: {}", jcrContent.getPath());

                // Add the custom property
                jcrContent.setProperty("mytest1", "testproperty1");

                // Save changes
                Session session = resourceResolver.adaptTo(Session.class);
                if (session != null) {
                    session.save();
                    log.info("Property added successfully to asset at {}", payloadPath);
                } else {
                    log.error("Failed to adapt ResourceResolver to JCR Session.");
                }
            } else {
                log.error("jcr:content node does not exist for asset at path: {}", payloadPath);
            }
        } catch (Exception e) {
            log.error("Error adding property to asset", e);
        }
    }
}