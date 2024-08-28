package com.mysite.core.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysite.core.service.WeatherApiConfigService;
import org.apache.commons.io.IOUtils;
//import com.google.gson.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

//import javax.annotation.PostConstruct;


@Model(adaptables= SlingHttpServletRequest.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Task1 {

    public static final Logger LOGGER = LoggerFactory.getLogger(Task1.class);
    @ValueMapValue
    private String pin;


    private String temperature;

    private String pressure;

    private String placeName;

    private String humidity;

    private String seaLevel;

    private String groundLevel;

    private String sunrise;

    private String sunset;

    private String speed;

    private String degree;

    private String main1;
  private String weather2;

  @OSGiService
  private WeatherApiConfigService weatherApiConfigService;

    public String getPin() {
        LOGGER.info("start...");
        return pin;
    }

    @PostConstruct
    protected void init() {
        if (pin != null && !pin.isEmpty()) {
            fetchWeatherData(pin);
        }
    }

    private void fetchWeatherData(String pin) {
        String apiKey = weatherApiConfigService.getApiKey();
        String apiUrl = String.format(weatherApiConfigService.getApiUrl(), pin, apiKey);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    parseWeatherData(result);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error fetching weather data", e);
        }
    }
    private void parseWeatherData(String json) {

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonObject main = jsonObject.getAsJsonObject("main");
        JsonObject sys = jsonObject.getAsJsonObject("sys");
        JsonObject wind = jsonObject.getAsJsonObject("wind");
        JsonArray weather = jsonObject.getAsJsonArray("weather");


        temperature = main.get("temp").getAsString();
        placeName = jsonObject.get("name").getAsString();
        pressure = main.get("pressure").getAsString();
        humidity = main.get("humidity").getAsString();
        seaLevel = main.get("sea_level").getAsString();
        groundLevel = main.get("grnd_level").getAsString();
        sunrise = sys.get("sunrise").getAsString();
        sunset = sys.get("sunset").getAsString();
        speed = wind.get("speed").getAsString();
        degree = wind.get("deg").getAsString();

        JsonObject weather1 =weather.get(0).getAsJsonObject();
        weather2=weather1.get("description").getAsString();
        main1 = weather1.get("main").getAsString();


    }


    public String getTemperatureData() {
        return temperature;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public String getGrandLevel() {
        return groundLevel;
    }

    public String getSunrise() {
        return sunrise;

    }

    public String getSunset() {
        return sunset;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDegree() {
        return degree;
    }


   public String getMain(){
        return main1;
   }
    public String getDescription(){

        return weather2;
  }
  }


