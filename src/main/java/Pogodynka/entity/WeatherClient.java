package Pogodynka.entity;

import java.io.IOException;
import java.net.URISyntaxException;

public interface WeatherClient {

    public WeatherForecast getWeatherForTommorow(String cityName) throws IOException, InterruptedException, URISyntaxException;
    public WeatherForecast getWeatherFromPast(String cityName, String date) throws IOException, InterruptedException, URISyntaxException;
}
