package com.test.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Product", catalog = "test")
public class Product implements Serializable {

    @Column(name = "Name", unique = true, nullable = true)
    @Size(min = 3, max = 15, message = "Name must be >3 and <15 characters")
    private String name;

    @Column(name = "Price", unique = false, nullable = true, length = 10)
    @Min(value = 1, message = "You can't choose less than 1.")
    @Max(value = 1000, message = "You can't choose more than 10000.")
    private int price;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ProductId", unique = true, nullable = false)
    private Integer ID;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>(0);

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
//    private Set<Order> orders = new HashSet<Order>(0);
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String toString() {
        return "Product name " + name + ". Product price " + price;
    }
}
