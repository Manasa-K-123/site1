package com.mysite.core.models;



import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@Model(adaptables= SlingHttpServletRequest.class,adapters = ServiceDemo1.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ServiceDemo implements ServiceDemo1 {
    private static  final Logger LOG= LoggerFactory.getLogger(ServiceDemo.class);

    @Override
    public List<String> getPageTitle() {
        return null;
    }
    @PostConstruct
    protected void init(){
        LOG.info("====printing log from trace====");
        System.out.println("hi");

    }
}
