package com.mysite.core.models;



import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Servlet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Model(
        adaptables = { Resource.class },
        resourceType = "mysite/components/page",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class Model1 {

    private static final Logger LOG = LoggerFactory.getLogger(Model1.class);

    @Inject
    private Resource resource;

    @Inject
    private ResourceResolver resourceResolver;

    @Named("title")
    private String title;

    // Method to fetch data from a servlet
    public String getServletResponse() {
        String servletPath = "/bin/example/servlet";
        StringBuilder responseString = new StringBuilder();

        try {
            URL url = new URL(servletPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    responseString.append(inputLine);
                }
            }

        } catch (Exception e) {
            LOG.error("Error calling servlet: ", e);
        }

        return responseString.toString();
    }

    public String getTitle() {
        return title;
    }
}
