package com.test.DaoImplementation;

import com.test.DaoInterfaces.OrderDAO;
import com.test.model.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order getOrderById(int Id) {
        Query query = em.createQuery("from Orders where ID=:orderID ");
        query.setParameter("orderID", Id);
        Order order = (Order) query.getSingleResult();
        logger.info("Order successfully loaded. Order details: " + order);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        em.merge(order);
        logger.info("Order successfully added. Order details: " + order);
    }

    @Override
    public void deleteOrder(Order order) {
        Order order2 = new Order();
        Query query = em.createQuery("from Order where Id=:OrderID ");
        query.setParameter("OrderID", order.getId());
        order2 = (Order) query.getSingleResult();
        if (order2 != null) {
            em.remove(order2);
        }
        logger.info("Order successfully removed. Order details: " + order2);
    }

    @Override
    public Integer getLastOrderId() {
        Query query = em.createQuery("select max(Id) from Order");
        Integer result = (Integer) query.getSingleResult();
        return result;
    }

}
