package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    public void deleteUsers();

    public User findUserByCar (String module, String series);
}
