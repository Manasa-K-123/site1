package com.mysite.core.models;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.apache.sling.api.resource.Resource;

@Model(adaptables= Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name="exporter",extensions="type",selector = "selector")
//options = {
//        @ExporterOption(name=)
//}

public class AuthorImpl1 {
    @ValueMapValue
    private String fname;

    @ValueMapValue
    private  String lname;

    @ValueMapValue
    private  String profession;


    public String getFirstName() {
        return fname;
    }


    public String getLastName() {
        return lname;
    }


    public String getIsProfession() {
        return  profession;
    }
}
