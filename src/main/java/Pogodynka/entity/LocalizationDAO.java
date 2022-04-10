package Pogodynka.entity;

import Pogodynka.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class LocalizationDAO {

    static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    static final EntityManager entityManager = sessionFactory.createEntityManager();

    public static void generateLocalization(){
        Scanner scanner = new Scanner(System.in);
        Localization localization = new Localization();
        System.out.println("Please type in name of the City: ");
        localization.setCityName(scanner.nextLine());
        System.out.println("Please type in country name: ");
        localization.setCountryName(scanner.nextLine());
        System.out.println("Please type in region: (optional)");
        localization.setRegionName(scanner.nextLine());
        System.out.println("Please type in longitude: (optional)");
        localization.setLongitude(scanner.nextLine());
        System.out.println("Please type in latitude: (optional)");
        localization.setLatitude(scanner.nextLine());
    }

    public static void addLocalization(Localization localization){
        entityManager.getTransaction().begin();

        entityManager.persist(localization);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void displayAddedLocalizations(){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from Localization", Localization.class);
        List<Localization> localizationList = query.getResultList();

        localizationList.forEach(l -> System.out.println(l.getCityName() + " " + l.getCountryName()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void getWeatherInfo(){
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from WeatherForecast", WeatherForecast.class);
        List<WeatherForecast> weatherForecastList = query.getResultList();

        weatherForecastList.forEach(w -> System.out.println(w));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
