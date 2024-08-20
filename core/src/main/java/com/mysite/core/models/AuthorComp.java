package com.mysite.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorComp {

    @ValueMapValue
    private String fname;

    @ValueMapValue
    private String profession;

    @ValueMapValue
    private String lname;

    public String getFirstname() {
        return fname;
    }

    public String getProfession() {
        return profession;
    }

    public String getLastname() {
        return lname;
    }
}
