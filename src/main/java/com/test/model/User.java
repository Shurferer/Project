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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Users", catalog = "test")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "UserId", unique = true, nullable = false)
    private Integer Id;

    @Column(name = "Login", unique = true, nullable = false)
    @Size(min = 3, max = 30, message = "Login must be >3 characters")
    private String login;

    @Column(name = "Password", unique = false, nullable = false)
    @Size(min = 3, max = 30, message = "Password must be >3 characters")
    private String password;

    @Column(name = "Name", unique = false, nullable = false)
    @Size(min = 3, max = 30, message = "Name must be >3 characters")
    private String name;

    @Column(name = "Phone", unique = false, nullable = true, length = 10)
    @Min(value = 200000000, message = "Phone must be like 291111111 and starts from 29")
    private long phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<Order>(0);

    @Column(name = "BlackList", unique = false, nullable = false)
    private boolean addedToBlackList = false;

    public boolean isAddedToBlackList() {
        return addedToBlackList;
    }

    public void setAddedToBlackList(boolean addedToBlackList) {
        this.addedToBlackList = addedToBlackList;
    }

    public User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". Phone : " + phone;
    }

}
