package net.engineeringdigest.journalApp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public static class Current {

        private int temperature;

        private int feelslike;

        private int humidity;
    }
}