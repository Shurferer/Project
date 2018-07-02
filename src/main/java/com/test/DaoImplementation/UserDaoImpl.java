package com.test.DaoImplementation;

import com.test.DaoInterfaces.UserDAO;
import com.test.model.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.slf4j.LoggerFactory;

@Repository
public class UserDaoImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUserToDataBase(User user) {
        em.merge(user);
        logger.info("User successfully added. User details: " + user);
    }

    @Override
    public boolean checkUserCreated(String login) {
        boolean result = false;
        try {
            Query query = em.createQuery("from User where login=:login ");
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();
            result = true;
        } catch (Exception e) {
            System.out.println("Cheking user created - User with this login is not created.");
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        Query query = em.createQuery("from User where login=:login ");
        query.setParameter("login", login);
        User user = (User) query.getSingleResult();
        logger.info("User successfully loaded. User details: " + user);
        return user;
    }

    @Override
    public User getUserByID(int id) {
        Query query2 = em.createQuery("from User where Id=:userID ");
        query2.setParameter("userID", id);
        User client = (User) query2.getSingleResult();
        logger.info("User successfully loaded. User details: " + client);
        return client;
    }

    @Override
    public boolean checkAccess(User client) {
        boolean result = false;
        try {
            Query query = em.createQuery(" from User  where login=:login");
            query.setParameter("login", client.getLogin());
            User user = (User) query.getSingleResult();
            if (!(user == null) && (user.getPassword().equals(client.getPassword()))) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("Cheking user created - User with this login is not created.");
        }
        return result;
    }

    @Override
    public List<User> listUsers() {
        Query query = em.createQuery("from User");
        List<User> users = query.getResultList();
        for (User user : users) {
            logger.info("List product details: " + user);
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
        logger.info("User successfully added. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
         User user = new User();
        Query query = em.createQuery("from User where Id=:userID ");
        query.setParameter("userID", id);
        user = (User) query.getSingleResult();
        if (user != null) {
            em.remove(user);
        }
        logger.info("User successfully removed. User details: " + user);
    }
}
