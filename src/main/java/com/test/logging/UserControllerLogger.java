package com.test.logging;

import com.test.model.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

@Aspect
public class UserControllerLogger {

    private static final Logger userLogger = LogManager.getLogger(UserControllerLogger.class);

    @After("execution(* com.test.controller.UserController.loginAccount(..))")
    public void loginAccountUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        User user = (User) params[2];
        userLogger.info("User with login " + user.getLogin() + " entered the app.");
    }
    
    @After("execution(* com.test.controller.UserController.registrationUser(..))")
    public void registrationUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        User user = (User) params[1];
        userLogger.info("User try to register user with login " + user.getLogin());
    }
    
    @After("execution(* com.test.controller.UserController.removeUser(..))")
    public void removeUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        userLogger.info("User with id " + params[0]+" successfully removed.");
    }
    
    @After("execution(* com.test.controller.UserController.editUser(..))")
    public void editUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        userLogger.info("User with id " + params[0]+" added to black list.");
    }
    
    @After("execution(* com.test.controller.UserController.deleteFromBlackList(..))")
    public void deleteFromBlackListLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        userLogger.info("User with id " + params[0]+" deleted from black list.");
    }
}
