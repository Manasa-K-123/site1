package com.mysite.core.models;



import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WeatherMode {

    private static final Logger Log = LoggerFactory.getLogger(WeatherMode.class);

    @ValueMapValue
    private int latitude;

    @ValueMapValue
    private int longitude;

   private String icon;


    private String temperature;
    private String humidity;
    private String placeName;
    private String description;
 String apiUrl ;
//    "http://localhost:4502/bin/weatherdata?lat="+lat+"&lon="+longi;

    @PostConstruct
    protected void init() throws IOException {
        apiUrl ="http://localhost:4502/bin/weatherdata?lat="+latitude+"&lon="+longitude;
        BasicCredentialsProvider crdpro = new BasicCredentialsProvider();
        crdpro.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("admin","admin"));
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(crdpro).build();
        HttpGet get = new HttpGet(apiUrl);
        Log.info("getHttpClient "+get);
        HttpResponse get1 = httpClient.execute(get);
        Log.info("Response"+get1);
        HttpEntity h = get1.getEntity();
        Log.info("response1"+h);
        String s1 = EntityUtils.toString(h);
        Log.info("hello"+s1);
        JsonObject jsonObject = JsonParser.parseString(s1).getAsJsonObject();
        Log.info("jsonObject "+jsonObject);
                  temperature = jsonObject.get("temperature").getAsString();
                    humidity = jsonObject.get("humidity").getAsString();
                    placeName = jsonObject.get("placeName").getAsString();
                    description = jsonObject.get("description").getAsString();
                    icon=jsonObject.get("icon").getAsString();
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}

