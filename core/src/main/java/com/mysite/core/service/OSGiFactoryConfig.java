package com.mysite.core.service;

import java.util.List;

public interface OSGiFactoryConfig {
    public int getConfigID();
    public String getServiceName();
    public String getServiceURL();
    public List<OSGiFactoryConfig> getAllConfigs();

}
