package pogodynka.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherStackClient implements WeatherClient{

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public WeatherStackClient(ObjectMapper objectMapper, HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    // weatherstack api access key: 78ecbfcea890e12600f6a07f86da4196

    public WeatherForecast getWeatherForTommorow(String cityName) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherstack.com/forecast\n" +
                        "?access_key=78ecbfcea890e12600f6a07f86da4196\n" +
                        "&query="+ cityName +"\n" +
                        "&forecast_days=1\n" +
                        "&hourly=0"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), WeatherStackDTO.class).toDomain();

    }

    // nie zadziała bo tylko bierzaca pogoda działa z tego serwisu
    public WeatherForecast getWeatherFromPast(String cityName, String date) throws IOException, InterruptedException, URISyntaxException { //date format rrrr-mm-dd, after july 2008
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://api.weatherstack.com/historical\n" +
                        "?access_key=1afdcef52f75b2976fb75a3e3b2321c2\n" +
                        "&query="+cityName +"\n" +
                        "&historical_date="+ date +"\n" +
                        "&hourly = 0"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), WeatherStackDTO.class).toDomain();
        // throws new RuntimeExeption("")
    }
    // tutaj dorabiam kolejne zapytania, nazwać odpowiednio do warunków i serwisów(osobny serwis - osobny klient)
}
