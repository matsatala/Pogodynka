package Pogodynka.entity;

import Pogodynka.hibernate.HibernateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Date;
import java.util.List;

public class WeatherForecastAverageDAO {




    HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    WeatherClient openWeatherMapClient = new OpenWeatherMapClient(objectMapper, httpClient);
    WeatherClient weatherStackClient = new WeatherStackClient(objectMapper,httpClient);

    public boolean checkLocalization(String cityName){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        List<Localization> locList = entityManager.createQuery("from Localization l where l.cityName = :cityName", Localization.class)
                        .setParameter("cityName", cityName)
                                .getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return locList.size() > 0;
    }

    public WeatherForecastAverage saveAverageWeatherForTommorow(String cityName) throws IOException, URISyntaxException, InterruptedException {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        WeatherForecastAverage weatherForecastAverage = new WeatherForecastAverage();

        WeatherForecast weatherStackForecast = weatherStackClient.getWeatherForTommorow(cityName);
        WeatherForecast openWeatherForecast = openWeatherMapClient.getWeatherForTommorow(cityName);


        weatherForecastAverage.setTemperature((weatherStackForecast.getTemp() + openWeatherForecast.getTemp()) /2);
        weatherForecastAverage.setPressure((weatherStackForecast.getPressure() + openWeatherForecast.getPressure()) / 2);
        weatherForecastAverage.setHumidity((weatherStackForecast.getHumidity() + openWeatherForecast.getHumidity()) / 2);
        weatherForecastAverage.setWindDirection((weatherStackForecast.getWindDirection() + openWeatherForecast.getWindDirection()) / 2);
        weatherForecastAverage.setWindSpeed((weatherStackForecast.getWindSpeed() + openWeatherForecast.getWindSpeed()) / 2);
        weatherForecastAverage.setDate(new Date());

        entityManager.persist(weatherForecastAverage);

        entityManager.getTransaction().commit();
        entityManager.close();
        // przerobić w wolnym czasie
        // dodać lokalizację
        return weatherForecastAverage;
    }

    public void getAverageWeatherForTommorow(String cityName){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        WeatherForecastAverage weatherForecastAverage = entityManager.createQuery("select wfa from WeatherForecastAverage wfa "+
                "join fetch wfa.localization " +
                "where wfa.localization = :localization", WeatherForecastAverage.class)
                .setParameter("localization", cityName)
                .getSingleResult();

        System.out.println(weatherForecastAverage);

        entityManager.getTransaction().commit();
        entityManager.close();
    }




    // metoda do pobierania prognozy na jutro

    // metoda do pobierania prognozy na wyznaczony dzień
}
