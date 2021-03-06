package Pogodynka.entity;

import Pogodynka.hibernate.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class LocalizationDAO {



    public void addLocalization(Localization localization){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(localization);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void displayAddedLocalizations(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from Localization", Localization.class);
        List<Localization> localizationList = query.getResultList();

        localizationList.forEach(l -> System.out.println(l.getCityName() + " " + l.getCountryName()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void getWeatherInfo(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("from WeatherForecast", WeatherForecast.class);
        List<WeatherForecast> weatherForecastList = query.getResultList();

        weatherForecastList.forEach(w -> System.out.println(w));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
