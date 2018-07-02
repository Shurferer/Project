package com.test.DaoInterfaces;

import com.test.model.Order;

public interface OrderDAO {

    Order getOrderById(int Id);

    void addOrder(Order order);

    void deleteOrder(Order order);
    
    Integer getLastOrderId();

}
