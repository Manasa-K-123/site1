package com.mysite.core.models;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestResource {
    private static Logger log= LoggerFactory.getLogger(TestResource.class);
    @ValueMapValue
    private String title;
    @ValueMapValue
    private String description;

    public String getTitle() {
        log.info("title"+title);
        return title;
    }
    public String getDescription(){
        log.info("disription"+description);
        return description;
    }
}
