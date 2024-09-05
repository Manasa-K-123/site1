package com.mysite.core.service;


import com.mysite.core.config.GeeksOSGiConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=OSGIConfigModule.class,immediate = true)
@Designate(ocd= GeeksOSGiConfig.class)
public class OSGIConfigModuleImpl implements OSGIConfigModule {

    private int serviceId;
    private String serviceName;
    private String serviceURL;



    @Activate
    protected  void activate(GeeksOSGiConfig geeksOSGiConfig){
        serviceId=geeksOSGiConfig.serviceID();
        serviceName= geeksOSGiConfig.serviceName();
        serviceName= geeksOSGiConfig.serviceURL();
    }
    @Override
    public int getServiceId() {
        return serviceId;
    }

    @Override
    public String getServiceNameModule() {
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        return serviceURL;
    }
}
