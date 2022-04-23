package Pogodynka.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.Date;

// tworzymy klasę reprezentującą jsona i mapujmey przez nią
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherStackDTO {

    public Current current;

    public WeatherForecast toDomain() {

        return new WeatherForecast(this.current.temperature, this.current.humidity, this.current.pressure,
                this.current.windSpeed, this.current.windDegree, Date.from(Instant.now()));

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {

        public float temperature;
        public int humidity;
        public int pressure;
        @JsonProperty("wind_speed")
        public float windSpeed;
        @JsonProperty("wind_degree")
        public int windDegree;
    }


}



