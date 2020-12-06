package interlayer.hibernateUtil;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateInteraction {
    /** Фабрика сессий Hibernate */
    private static SessionFactory sessionFactory;

    private HibernateInteraction() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
