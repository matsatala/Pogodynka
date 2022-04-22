package Pogodynka.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;

public class HibernateUtil {

    public static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public static final EntityManager entityManager = sessionFactory.createEntityManager();

    private static final SessionFactory sf = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
            return sf;
        }
        public static void shutdown() {
            getSessionFactory().close();
        }

}
