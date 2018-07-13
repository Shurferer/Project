package com.test.DaoImplementation;

import com.test.DaoInterfaces.OrderDAO;
import com.test.model.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order getOrderById(int Id) {
        Query query = em.createQuery("from Orders where ID=:orderID ");
        query.setParameter("orderID", Id);
        Order order = (Order) query.getSingleResult();
        return order;
    }

    @Override
    public void addOrder(Order order) {
        em.merge(order);
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
    }

    @Override
    public Integer getLastOrderId() {
        Query query = em.createQuery("select max(Id) from Order");
        Integer result = (Integer) query.getSingleResult();
        return result;
    }

}
