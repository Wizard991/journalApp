package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {

        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);

        if(weatherResponse != null){
            System.out.println("FROM REDIS");
            return weatherResponse;
        }
        else {
            System.out.println("FROM API");

            String apiKey = appCache.appCache.get("weather_api");

            String url = "http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;

            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

            redisService.set("weather_of_" + city, response, 300L);

            return response;
        }
    }
}



