package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service= Servlet.class)
@SlingServletPaths(value = "/bin/details")



public class ServletExample extends SlingSafeMethodsServlet {
    @Override
    protected void doGet( SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);
        JsonObjectBuilder object= Json.createObjectBuilder();
        object.add("name","Manasa");
        object.add("site","www.nextrow.com");
        response.getWriter().write(object.build().toString());
    }





//        public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
//       JsonObjectBuilder object= Json.createObjectBuilder();
//        object.add("name","Manasa");
//        object.add("site","www.nextrow.com");
//        res.getWriter().write(object.build().toString());
//    }


}
