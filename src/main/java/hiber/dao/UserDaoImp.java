package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User UsersCarBySeries(int series) {
        Query query = (Query) sessionFactory.getCurrentSession().createQuery("from Car WHERE series = :paramName");
        query.setParameter("paramName", series);
        List<Car> car = query.getResultList();
        User user = sessionFactory.getCurrentSession().get(User.class, car.get(0).getId());
        return user;
    }
}
