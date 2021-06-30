package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    @ElementCollection
    @CollectionTable(joinColumns={ @JoinColumn(name="order_id") })
    @Column(name="piece")
    @MapKeyJoinColumn(name="product_id")
    private Map<Product,Integer> content;

    public Order(){}

    public Order(Long customerId, Map<Product, Integer> content) {
        this.customerId = customerId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Map<Product, Integer> getContent() {
        return content;
    }

    public void setContent(Map<Product, Integer> content) {
        this.content = content;
    }
}
