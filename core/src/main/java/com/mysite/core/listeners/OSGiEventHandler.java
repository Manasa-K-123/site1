package com.mysite.core.listeners;



import com.mysite.core.utils.ResolverUtil;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;

@Component(service = EventHandler.class,
        immediate = true,
        property = {
                EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
                EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
                EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
                EventConstants.EVENT_FILTER +"=(path=/content/mysite/us/en/osgipage/*)"
        })
public class OSGiEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(OSGiEventHandler.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    public void handleEvent(final Event event) {
        LOG.info("\n Resource event: {} at: {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));
        try {
            ResourceResolver resourceResolver= ResolverUtil.newResolver(resourceResolverFactory);

            Resource resource=resourceResolver.getResource(event.getProperty(SlingConstants.PROPERTY_PATH).toString());

            Node node=resource.adaptTo(Node.class);

            node.setProperty("eventhandlertask","Event "+event.getTopic()+" by "+resourceResolver.getUserID());

            for(String prop : event.getPropertyNames()){
                LOG.info("\n Property : {} , Value : {} ", prop,event.getProperty(prop));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
}
