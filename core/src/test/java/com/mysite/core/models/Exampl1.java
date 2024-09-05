package com.mysite.core.models;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import com.day.cq.wcm.api.Page;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

@Model(adaptables= SlingHttpServletRequest.class)
public class Exampl1 {

    // @ScriptVariable (name=currentPage)
    Page page;

    public String getPath(){
        return page.getPath();
    }

}
