package com.mysite.core.service;



import com.mysite.core.config.OpenWeatherMapConfig;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component(service = WeatherService.class, immediate = true)
@Designate(ocd = OpenWeatherMapConfig.class)
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private OpenWeatherMapConfig config;

    @Activate
    @Modified
    protected void activate(OpenWeatherMapConfig config) {
        this.config = config;
    }


    public JSONObject getWeatherData(String city) {
        if (StringUtils.isBlank(city)) {
            log.error("City is null or empty.");
            return null; // Return null if city is null or empty
        }

        try {
            String cityUrl = config.baseUrl() + city + "&appid=" + config.apiKey() + "&units=metric";
            //String cityUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=0526677276a44519a33bb9139141c966&units=metric";
            log.info("City URL: {}", cityUrl);

            HttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet(cityUrl);
            HttpResponse httpResponse = httpClient.execute(getRequest);

            // Check if the response is successful (status code 200)
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String responseBody = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
                return new JSONObject(responseBody.toString());
            } else {
                log.error("Failed to fetch weather data for city: {}. Status code: {}", city, httpResponse.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            log.error("Error fetching weather data for city: {}", city, e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    private JSONObject parseWeatherData(String responseBody) {
        try {
            JSONObject weatherData = new JSONObject(responseBody);
            JSONObject coordData = weatherData.getJSONObject("coord");
            JSONObject main = weatherData.getJSONObject("main");
            JSONObject wind = weatherData.getJSONObject("wind");
            JSONObject clouds = weatherData.getJSONObject("clouds");
            JSONObject sys = weatherData.getJSONObject("sys");
            JSONObject parsedData = new JSONObject();
            parsedData.put("coord", coordData);
            parsedData.put("main", main);
            parsedData.put("wind", wind);
            parsedData.put("clouds", clouds);
            parsedData.put("sys", sys);
            return parsedData;
        } catch (JSONException e) {
            log.error("Error parsing weather data: {}", e.getMessage());
            return null;
        }
    }


}
