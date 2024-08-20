package com.mysite.core.models;

import com.mysite.core.service.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import java.util.List;

@Model(adaptables = SlingHttpServletRequest.class,adapters = OSGiConfigDemo.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class OSGiConfigDemoImpl implements OSGiConfigDemo {


    @OSGiService
    OSGiConfig OSGiConfig;
    @OSGiService
    OSGIConfigModule osgiConfigModule;

    @OSGiService
    OSGiFactoryConfig osGiFactoryConfig;

    @Override
    public String getServiceName() {
        return OSGiConfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        return OSGiConfig.getServiceCount();
    }

    @Override
    public boolean isLiveData() {
        return OSGiConfig.isLiveData();
    }

    @Override
    public String[] getCountries() {
        return OSGiConfig.getCountries();
    }

    @Override
    public String getRunModes() {
        return OSGiConfig.getRunModes();
    }

    @Override
    public int getServiceId() {
        return osgiConfigModule.getServiceId();
    }

    @Override
    public String getServiceNameModule() {
        return osgiConfigModule.getServiceNameModule();
    }

    @Override
    public String getServiceURL() {
        return osgiConfigModule.getServiceURL();
    }

    @Override
    public List<OSGiFactoryConfig> getAllConfigs() {
        return osGiFactoryConfig.getAllConfigs();
    }


}
