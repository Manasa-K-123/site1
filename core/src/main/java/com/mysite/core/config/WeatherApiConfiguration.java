package com.mysite.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Weather API Configuration")
public @interface WeatherApiConfiguration {
    @AttributeDefinition(name = "API Key", description = "API Key for the Weather API")
    String apiKey() default "1f87e5de93f1954a9246f0b344a4558a";

    @AttributeDefinition(name = "API URL", description = "URL for the Weather API")
    String apiUrl() default "https://api.openweathermap.org/data/2.5/weather?zip=%s,in&appid=%s&units=imperial";

}