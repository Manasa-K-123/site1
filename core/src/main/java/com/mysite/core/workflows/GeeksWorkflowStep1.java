package com.mysite.core.workflows;


//import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = WorkflowProcess.class,
        immediate = true,
        property = {
                "process.label" +" = Geeks Workflow Step",
                Constants.SERVICE_VENDOR +" =AEM GEEKS",
                Constants.SERVICE_DESCRIPTION + " = Custom geeks workflow step"
        }
)

public class GeeksWorkflowStep1 implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(GeeksWorkflowStep1.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguements)  {
        LOG.info("===custom work flow step====");
    }
}





