package com.test.DaoInterfaces;

import com.test.model.User;
import java.util.List;

public interface UserDAO {

    void addUserToDataBase(User user);

    boolean checkUserCreated(String login);

    User getUserByLogin(String login);

    public User getUserByID(int id);

    List<User> listUsers();

    boolean checkAccess(User client);

    void updateUser(User user);

    void removeUser(int id);

}
