package com.mysite.core.service;

import com.mysite.core.config.WeatherApiConfiguration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = WeatherApiConfigService.class)
@Designate(ocd = WeatherApiConfiguration.class)
public class WeatherApiConfigService {

    private volatile String apiKey;
    private volatile String apiUrl;

    @Activate
    @Modified
    protected void activate(WeatherApiConfiguration config) {
        this.apiKey = config.apiKey();
        this.apiUrl = config.apiUrl();
    }
    public String getApiKey() {
        return apiKey;
    }
    public String getApiUrl() {
        return apiUrl;
    }
}

