package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void deleteUsers() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public User findUserByCar(String model, String series) {
        return sessionFactory.getCurrentSession().createQuery("from User user  where  user.userCar.model = ?1  " +
                        "and user.userCar.series = ?2", User.class)
                .setParameter(1, model)
                .setParameter(2, series).uniqueResult();
    }


}
