package com.test.logging;

import com.test.model.Order;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

@Aspect
public class OrderDaoImplLogger {

    private static final Logger orderLogger = LogManager.getLogger(OrderDaoImplLogger.class);

    @After("execution(* com.test.DaoImplementation.OrderDaoImpl.getOrderById(..))")
    public void getOrderByIdLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        orderLogger.info("Order successfully loaded. Order id: " + params[0]);
    }

    @After("execution(* com.test.DaoImplementation.OrderDaoImpl.addOrder(..))")
    public void addOrderLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        Order order = (Order) params[0];
        orderLogger.info("Order successfully added. Order details: " + order);
    }

    @After("execution(* com.test.DaoImplementation.OrderDaoImpl.deleteOrder(..))")
    public void deleteOrderLogAfter(JoinPoint jp) {
        Object[] params = jp.getArgs();
        Order order = (Order) params[0];
        orderLogger.info("Order successfully removed. Order details: " + order);
    }
}

