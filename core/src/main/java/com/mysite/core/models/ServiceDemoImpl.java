package com.mysite.core.models;

import com.day.cq.wcm.api.Page;
import com.mysite.core.service.DemoService;
import com.mysite.core.service.DemoServiceB;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Iterator;
import java.util.List;

@Model(adaptables= SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo2 {
    private static final Logger LOG= LoggerFactory.getLogger(ServiceDemoImpl.class);


    @OSGiService
    DemoService demoService;



    @OSGiService
    DemoServiceB demoServiceB;

    @Override
    public Iterator<Page> getPageList() {
        LOG.info("PageList1");
        return demoService.getPages();
    }

    @Override
    public List<String> getPageTitleList() {
        return demoServiceB.getPages();
    }


}
