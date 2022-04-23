package Pogodynka.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapDTO {

    public MainWeather main;
    public Wind wind;

    public WeatherForecast toDomain(){

        return new WeatherForecast(this.main.temp, this.main.humidity, this.main.pressure, this.wind.speed, this.wind.deg, Date.from(Instant.now()));

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainWeather{

        public float temp;
        public int pressure;
        public int humidity;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind{

        public float speed;
        public int deg;
    }


}