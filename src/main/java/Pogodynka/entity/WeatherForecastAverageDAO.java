package Pogodynka.entity;

import pogodynka.hibernate.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.List;

public class WeatherForecastAverageDAO {


    EntityManager entityManager = HibernateUtil.entityManager;

    HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    WeatherClient openWeatherMapClient = new OpenWeatherMapClient(objectMapper, httpClient);
    WeatherClient weatherStackClient = new WeatherStackClient(objectMapper,httpClient);

    public boolean checkLocalization(String cityName){
        entityManager.getTransaction().begin();

        List<Localization> locList = entityManager.createQuery("Select from Localization l where l.cityName = :cityName", Localization.class)
                        .setParameter("cityName", cityName)
                                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return locList.size() > 0;
    }

    public void saveAverageWeatherForTommorow(String cityName) throws IOException, URISyntaxException, InterruptedException {
        entityManager.getTransaction().begin();
        WeatherForecastAverage weatherForecastAverage = new WeatherForecastAverage();

        WeatherForecast weatherStackForecast = weatherStackClient.getWeatherForTommorow(cityName);
        WeatherForecast openWeatherForecast = openWeatherMapClient.getWeatherForTommorow(cityName);
        // jeszcze z accu weather

        weatherForecastAverage.setTemperature((weatherStackForecast.getTemp() + openWeatherForecast.getTemp()) /3);
        weatherForecastAverage.setPressure((weatherStackForecast.getPressure() + openWeatherForecast.getPressure()) / 3);
        weatherForecastAverage.setHumidity((weatherStackForecast.getHumidity() + openWeatherForecast.getHumidity()) / 3);
        weatherForecastAverage.setWindDirection((weatherStackForecast.getWindDirection() + openWeatherForecast.getWindDirection()) / 3);
        weatherForecastAverage.setWindSpeed((weatherStackForecast.getWindSpeed() + openWeatherForecast.getWindSpeed()) / 3);

        entityManager.persist(weatherForecastAverage);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void getAverageWeatherForTommorow(String cityName){
        entityManager.getTransaction().begin();

        // skad wiedzieć dla któego miasta zapisaliśmy averageWeather?
        // relacja z localization 1 - 1 ?


        entityManager.getTransaction().commit();
        entityManager.close();
    }


    // metoda do pobierania prognozy na jutro

    // metoda do pobierania prognozy na wyznaczony dzień
}
