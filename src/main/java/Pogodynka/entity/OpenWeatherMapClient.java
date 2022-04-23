package Pogodynka.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherMapClient implements WeatherClient{

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public OpenWeatherMapClient(ObjectMapper objectMapper, HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    @Override
    public WeatherForecast getWeatherForTommorow(String cityName) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=11152b3e9411e308f0a32c582e1598ed"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), OpenWeatherMapDTO.class).toDomain();
    }

    @Override
    public WeatherForecast getWeatherFromPast(String cityName, String date) throws IOException, InterruptedException, URISyntaxException {
        return null;
    }
}