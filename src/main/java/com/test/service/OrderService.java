package com.test.service;

import com.test.DaoImplementation.OrderDaoImpl;
import com.test.model.Order;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDaoImpl orderDao;

    @Transactional
    public Order getOrderById(int Id) {
        return orderDao.getOrderById(Id);
    }

    @Transactional
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Transactional
    public void deleteOrder(Order order) {
        orderDao.deleteOrder(order);
    }

    @Transactional
    public Integer getLastOrderId() {
        return orderDao.getLastOrderId();
    }

}
