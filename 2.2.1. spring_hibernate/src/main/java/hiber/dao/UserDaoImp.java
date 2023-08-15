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
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model = ?1  and series = ?2")
                .setParameter(1, model)
                .setParameter(2, series);
        List<Car> car = query.getResultList();
        if (car.isEmpty()) {
            return null;
        }
        List<User> users = listUsers();
        User findUser = null;
        for (User user : users) {
            if (user.getUserCar().equals(car.get(0))) {
                findUser = user;
                break;
            }
        }
        return findUser;
    }


}
