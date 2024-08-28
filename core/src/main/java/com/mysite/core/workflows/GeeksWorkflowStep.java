package com.mysite.core.workflows;


import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;




import com.adobe.granite.workflow.metadata.MetaDataMap;


import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.Iterator;
import java.util.Set;

@Component(service = WorkflowProcess.class,
            immediate = true,
        property = {
        "process.label" +" = Geeks Workflow Process",
                Constants.SERVICE_VENDOR +" =AEM GEEKS",
                Constants.SERVICE_DESCRIPTION + " = Custom geeks workflow step"
        }
)

public class GeeksWorkflowStep implements WorkflowProcess {

    private  static final Logger LOG= LoggerFactory.getLogger(GeeksWorkflowStep.class);



    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments)  {
    LOG.info("===hi===");
    Session session = null;
        try {
            WorkflowData workflowData = workItem.getWorkflowData();
            if ("JCR_PATH".equals(workflowData.getPayloadType())) {
                session = workflowSession.adaptTo(Session.class);
                String path = workflowData.getPayload().toString() + "/jcr:content";
                Node node = (Node) session.getItem(path);
                String[] processArgs = processArguments.get("PROCESS ARGS", "string").toString().split(",");
                for (String WfArgs : processArgs) {
                    String[] args = WfArgs.split(":");
                    String prop = args[0];
                    String value = args[1];
                    if (node != null) {
                        node.setProperty(prop, value);
                    }
                }
                MetaDataMap wfd=workItem.getWorkflow().getWorkflowData().getMetaDataMap();
                Set<String> keyset=wfd.keySet();
                Iterator<String> i=keyset.iterator();
                while(i.hasNext()){
                    String key=i.next();
                    LOG.info("\n  ITEM  key - {},value - {}",key,wfd.get(key));
                }

            }
        }catch(Exception e) {
        }
    }


}

