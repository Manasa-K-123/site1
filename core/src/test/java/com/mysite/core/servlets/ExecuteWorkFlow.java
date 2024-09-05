package com.mysite.core.servlets;


import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
@Component(service = Servlet.class)
@SlingServletPaths( value = {
        "/bin/executeworkflow","/geeks/executeworkflow"
})

public class ExecuteWorkFlow  extends SlingSafeMethodsServlet {

    private static final Logger LOG= LoggerFactory.getLogger(ExecuteWorkFlow.class);

    @Override
    protected void doGet(final  SlingHttpServletRequest request,final SlingHttpServletResponse response) throws ServletException, IOException {
        String status= "Workflow Executing";
        final ResourceResolver resourceResolver=request.getResourceResolver();

        String payload=request.getRequestParameter("page").getString();

        try{
            if(StringUtils.isNotBlank(payload)){
                WorkflowSession workflowSession=resourceResolver.adaptTo(WorkflowSession.class);

                WorkflowModel  workflowModel=workflowSession.getModel("/var/workflow/models/workflow2");


                WorkflowData workflowData=workflowSession.newWorkflowData("JCR_PATH",payload);

                workflowSession.startWorkflow(workflowModel,workflowData);


            }
        }
        catch(Exception e){
            LOG.info("ERROR  IN WORKFLOW {}",e.getMessage());
        }
        response.setContentType("application/json");
        response.getWriter().write(status);
    }
}