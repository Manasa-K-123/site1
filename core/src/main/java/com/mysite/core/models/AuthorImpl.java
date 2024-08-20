package com.mysite.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


import javax.inject.Inject;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class AuthorImpl {
    @ValueMapValue
    public String text;

    @ValueMapValue
    public String text1;

    public String getTextValue(){

        return text;
    }
    public String getText1Value(){

        return text1;
    }
}
