package com.mysite.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.Optional;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;

    private String message;

    @PostConstruct
    protected void init() {
        if (resourceResolver == null || currentResource == null) {
            message = "ResourceResolver or Resource is not available.";
            return;
        }

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath).orElse("Page not found");

        message = "Hello World!\n"
                + "Resource type is: " + resourceType + "\n"
                + "Current page is:  " + currentPagePath + "\n";
    }

    public String getMessage() {
        return message;
    }

}
