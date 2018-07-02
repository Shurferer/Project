package com.test.service;

import com.test.DaoImplementation.UserDaoImpl;
import com.test.model.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserDaoImpl userDao;
    
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
    
    @Transactional
    public void addUserToDataBase(User user) {
        userDao.addUserToDataBase(user);
    }
    
    @Transactional
    public boolean checkUserCreated(String login) {
        return userDao.checkUserCreated(login);
    }
    
    @Transactional
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
    
    @Transactional
    public User getUserByID(int id) {
        return userDao.getUserByID(id);
    }
    
    @Transactional
    public boolean checkAccess(User client) {
        return userDao.checkAccess(client);
    }
    
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }
}
