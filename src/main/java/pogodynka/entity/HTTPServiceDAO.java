package pogodynka.entity;

import org.hibernate.SessionFactory;
import pogodynka.hibernate.HibernateUtil;

import javax.persistence.EntityManager;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

public class HTTPServiceDAO {

    private final static String URL_1 = "https://openweathermap.org";
    private final static String URL_2 = "https://accuweather.com";
    private final static String URL_3 = "https://weatherstack.com";


    public static List<HTTPService> addHttpServicesToDatabase (HTTPService httpService){
        HTTPService openWeatherMap = new HTTPService(URL_1);
        HTTPService accuWeather = new HTTPService(URL_2);
        HTTPService weatherStack = new HTTPService(URL_3);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(openWeatherMap);
        entityManager.persist(accuWeather);
        entityManager.persist(weatherStack);

        entityManager.getTransaction().commit();
        HibernateUtil.shutdown();

        return new ArrayList<HTTPService>(List.of(openWeatherMap,accuWeather,weatherStack));
    }

}
