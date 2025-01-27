package com.mysite.core.servlets;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import java.io.IOException;

    @Component(
            service = { Servlet.class },
            property = {
                    "sling.servlet.paths=/bin/example/servlet",
                    "sling.servlet.methods=GET"
            }
    )
    public class ExampleServlet extends SlingSafeMethodsServlet {

        @Override
        protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Hello from Servlet!\"}");
        }
    }



