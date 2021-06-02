package com.kodilla.restaurantbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @NotNull
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "orderList")
    private List<Product> productList = new ArrayList<>();

    public Order(User user, LocalDateTime orderDate, List<Product> productList) {
        this.user = user;
        this.orderDate = orderDate;
        this.productList = productList;
    }

    public Order(User user, LocalDateTime orderDate) {
        this.user = user;
        this.orderDate = orderDate;
    }
}