package com.test.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Orders", catalog = "test")
public class Order implements Serializable {

    @Id
    @Column(name = "OrderId", unique = true, nullable = false)
    private Integer Id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;

    @Column(name = "Date", unique = false, nullable = false)
    private Date date;

    @Column(name = "Amount", unique = false, nullable = false)
    @Min(value = 1, message = "You can't choose less than 1.")
    @Max(value = 100, message = "You can't choose more than 100.")
    private int numberOfProducts;

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User client) {
        this.user = client;
    }

    public String toStrig() {
        return "Order id: " + Id + ", made by " + user + "." +" Ordered product -"+ product.getName();
    }
}
