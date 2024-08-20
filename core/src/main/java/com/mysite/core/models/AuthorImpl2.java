package com.mysite.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
 import com.day.cq.wcm.api.Page;

import javax.inject.Inject;

@Model(adaptables= {Resource.class, SlingHttpServletRequest.class},adapters=Author2.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl2 implements Author2{
    @ScriptVariable
    Page currentPage;
   @Inject
   @Default(values="AEM")
    String fname;
   @Inject
   @Default(values="GEEKS")
   String lname;

   @Inject
    String profession;


    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public String getIsProfession() {
        return profession;
    }

    @Override
    public String getPageTitle() {
        return currentPage.getPageTitle();
    }
}
