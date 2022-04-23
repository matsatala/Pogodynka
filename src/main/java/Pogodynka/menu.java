package Pogodynka;

import Pogodynka.entity.*;
import Pogodynka.hibernate.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.hibernate.SessionFactory;


import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;

public class menu {
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherClient openWeatherMapClient = new OpenWeatherMapClient(objectMapper, httpClient);
        WeatherClient weatherStackClient = new WeatherStackClient(objectMapper,httpClient);
        LocalizationClient localizationClient = new LocalizationClient(objectMapper, httpClient);

        LocalizationDAO localizationDAO = new LocalizationDAO();


        WeatherForecastAverageDAO weatherForecastAverageDAO = new WeatherForecastAverageDAO();
        boolean isWorking = true;

        System.out.println("witamy w aplikacji!");
        Scanner scanner = new Scanner(System.in);
        while (isWorking){
            System.out.println("prosze wybrac opcje z menu\n" +
                    "1. Dodaj nową lokalizację \n" +
                    "2. Wyświetl aktualnie dodane lokalizacje \n" +
                    "3. Pobierz wartości pogodowe \n" +
                    "4.exit");
            String option = scanner.nextLine();
            switch (option){
                case "1":
                    Localization localization = new Localization();
                    System.out.println("Wpisz nazwę miasta: ");
                    String cityName = scanner.nextLine();
                    if (cityName != null && !cityName.equals("")){
                        localization.setCityName(cityName);
                    }else{
                        System.out.println("Wymagana nazwa miasta!");
                        continue;
                    }
                    System.out.println("Podaj nazwę kraju: ");
                    String countryName = scanner.nextLine();
                    if (countryName != null && !countryName.equals("")){
                        localization.setCountryName(scanner.nextLine());
                    }else {
                        System.out.println("Wymagana nazwa kraju!");
                        continue;
                    }
                    System.out.println("Podaj region: (nie wymagane)");
                    String region = scanner.nextLine();
                    localization.setRegionName(region);
                    System.out.println("Podaj długość geograficzną: (nie wymagane)");
                    int longitude = scanner.nextInt();
                    localization.setLongitude(longitude);
                    System.out.println("Podaj szerokość geograficzną: (nie wymagane)");
                    int latitude = scanner.nextInt();
                    localization.setLatitude(latitude);

                    localizationDAO.addLocalization(localization);
                    break;
                case "2":
                    localizationDAO.displayAddedLocalizations();
                    break;
                case "3":
                    System.out.println("Podaj miasto dla którego chcesz sparwdzić pogodę: ");
                    String userLocation = scanner.nextLine();
                    if(weatherForecastAverageDAO.checkLocalization(userLocation)){
                        System.out.println(weatherForecastAverageDAO.saveAverageWeatherForTommorow(userLocation));
                    }
                    else{
                        localizationDAO.addLocalization(localizationClient.getLocalizationFromName(userLocation));
                        System.out.println(weatherForecastAverageDAO.saveAverageWeatherForTommorow(userLocation));
                    }
                    // switch na date i miejsce lub samo miejsce lub if/else - czy chcesz wybrać pogode na jutro? jak nie to podaj datę
                    //
                    // if else sprawdzający czy dane są juz w bazie danych na daną lokalizację i date
                    // jesli nie ma to wysyłamy zapytanie do 3 serwisów i dane przekazujemy do bazy danych(pamiętać o FK serwisów)
                    break;
                case "exit":
                    isWorking = false;
                    break;
                default:
                    System.out.println("prosze wybrac dostepna opcje");
            }
        }


    }

    public static String getWetherWithCityName(String cityName) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=11152b3e9411e308f0a32c582e1598ed"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String result = response.body();
        return result;
    }


    public static String getJsonFromAPIWithCityOP(String cityName) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=11152b3e9411e308f0a32c582e1598ed&units=metric"))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static WeatherForecast getWeatherForecastObjectOP(String jsonString) {

        JsonObject object = (JsonObject) JsonParser.parseString(jsonString);
        JsonObject main = object.getAsJsonObject("main");
        JsonObject wind = object.getAsJsonObject("wind");
        float temp = main.get("temp").getAsFloat();
        int humidity = main.get("humidity").getAsInt();
        int pressure = main.get("pressure").getAsInt();
        float windSpeed = wind.get("speed").getAsFloat();
        int deg = wind.get("deg").getAsInt();

        return new WeatherForecast(temp, humidity, pressure, windSpeed, deg, Date.from(Instant.now()));
    }





//    public static WeatherForecast parseJson(String jsonString) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        OpenWeatherMapDTO dto = objectMapper.readValue(jsonString, OpenWeatherMapDTO.class);
//
//        return new WeatherForecast(dto.main.temp, dto.main.humidity, dto.main.pressure, dto.wind.speed, dto.wind.deg, Date.from(Instant.now()));
//    }

}
