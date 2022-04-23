package Pogodynka.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocalizationClient {
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public LocalizationClient(ObjectMapper objectMapper, HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    // trzeba dodac type wrappera bo mamy liste w jsonie

    public Localization getLocalizationFromName(String cityName) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=TJv1XvZB2f6M2znUneAuNSYwxYjwxUxl&q="+cityName))
                .build();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(),LocalizationDTO.class).toDomain();
    }
}