package com.mysite.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service={Servlet.class})
@SlingServletResourceTypes(resourceTypes = "mysite/components/title",
        selectors="myServlet",
        extensions="json",
        methods= HttpConstants.METHOD_POST
)


public class ServletExample1 extends SlingSafeMethodsServlet {

    public static final Logger LOGGER= LoggerFactory.getLogger(ServletExample1.class);
    private Object components;

    protected void doPost(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws ServletException , IOException{
        LOGGER.debug("Servlet code started");
    }
}