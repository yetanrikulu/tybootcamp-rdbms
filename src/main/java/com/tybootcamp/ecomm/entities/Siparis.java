package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Siparis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    @ElementCollection
    @CollectionTable(joinColumns={ @JoinColumn(name="order_id") })
    @Column(name="piece")
    @MapKeyJoinColumn(name="product_id")
    private Map<Product,Integer> content = new HashMap<>();

    public Siparis(){

    }

    public Siparis(Customer customer) {
        this.customerId = customer.getId();
        customer.getBasket().getContent().forEach((k,v) -> this.content.put(k,v));
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
