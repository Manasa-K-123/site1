package com.mysite.core.models;

import com.mysite.core.service.MultiService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables= SlingHttpServletRequest.class,adapters = ServiceDemo3.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl3  implements ServiceDemo3{
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl3.class);

    @OSGiService(filter = "(componnent.name=serviceA)")
    MultiService multiService;

    @OSGiService(filter = "(component.name=com.mysite.core.service.MultiServiceAImpl)")
    MultiService multiServiceB;

    @Override
    public String getNameFromService() {
        return multiService.getName();
    }

    @Override
    public String getNameFromServiceB() {
        return multiServiceB.getName();
    }


}
