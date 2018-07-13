package com.test.logging;

import com.test.model.Order;
import com.test.model.User;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

@Aspect
public class OrderControllerLogger {

    private static final Logger orderLogger = LogManager.getLogger(OrderControllerLogger.class);

    @Before("execution(* com.test.controller.OrderController.addProductToOrder(..))")
    public void addProductToOrderUserLogBefore(JoinPoint jp) {
        Object[] params = jp.getArgs();
        HttpSession session = (HttpSession) params[2];
        User user = (User) session.getAttribute("user");
        String info = "User with login " + user.getLogin() + " try to add product to order.";
        orderLogger.info(info);
    }

    @After("execution(* com.test.controller.OrderController.addProductToOrder(..))")
    public void addProductToOrderUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        Order order = (Order) params[3];
        String info = "Order with product " + order.getProduct().getName() + " successfully done.";
        orderLogger.info(info);
    }

    @Before("execution(* com.test.controller.OrderController.removeProductFromOrder(..))")
    public void removeProductFromOrderUserLogBefore(JoinPoint jp) {
        Object[] params = jp.getArgs();
        HttpSession session = (HttpSession) params[2];
        User user = (User) session.getAttribute("user");
        String info = "User with login " + user.getLogin() + " try to remove product with id " + params[0] + " from order.";
        orderLogger.info(info);
    }

    @After("execution(* com.test.controller.OrderController.removeProductFromOrder(..))")
    public void removeProductFromOrderUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        String info = "Product with id " + params[0] + " successfully removed.";
        orderLogger.info(info);
    }

    @After("execution(* com.test.controller.OrderController.saveUserOrder(..))")
    public void saveUserOrderUserLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        HttpSession session = (HttpSession) params[0];
        User user = (User) session.getAttribute("user");
        String info = "User with login " + user.getLogin()+" save new order.";
        orderLogger.info(info);
    }
}
