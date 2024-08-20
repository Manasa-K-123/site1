package com.mysite.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import javax.inject.Inject;
import javax.inject.Named;

//this is model class
@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ArticleDemoImpl {

    @ScriptVariable
    private Page currentPage;


    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String title;

    @Self
    SlingHttpServletRequest slingHttpServletRequest;

      //    @ValueMapValue
    @ValueMapValue
    private String description;

    @ValueMapValue
    private int dob;

    @ValueMapValue
    private String image;
//
//    @Override
//    public String getText() {
//        return title1;
//    }
//
//    @Override
//    public String getDescription() {
//        return description1;
//    }
//
//    @Override
//    public int getDate() {
//        return dob;
//    }
//
//    @Override
//    public String getImage() {
//        return image;
//    }
//
//    @Override
//    public String getPageTitle() {
//        return currentPage.getPageTitle();
//    }


    public Page getCurrentPage() {
        return currentPage;
    }

    public Resource getResource() {
        return resource;
    }

    public String getTitle() {
        return title;
    }

    public SlingHttpServletRequest getSlingHttpServletRequest() {
        return slingHttpServletRequest;
    }

    public String getDescription() {
        return description;
    }

    public int getDob() {
        return dob;
    }

    public String getImage() {
        return image;
    }
}