package interlayer.dao;

import interlayer.hibernate.HibernateSessionFactory;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Data Access Object для модели {@link model.User}
 */
public class UserDAO {

    private SessionFactory sessionFactory;

    public UserDAO() {
        this.sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    public User getById(long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public User getByLogin(String login) {
        try(Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(User.class);
            return (User) criteria.add(Restrictions.eq("login", login)).uniqueResult();
        }
    }

    public long addUser(User user) {
        try(Session session = sessionFactory.openSession()) {
            return (long) session.save(user);
        }
    }
}
