package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Basket {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    @CollectionTable(joinColumns={ @JoinColumn(name="basket_id") })
    @Column(name="piece")
    @MapKeyJoinColumn(name="product_id")
    private Map<Product,Integer> content = new HashMap();

    public Basket() {
    }

    public Basket(Map<Product, Integer> content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<Product, Integer> getContent() {
        return content;
    }

    public void setContent(Map<Product, Integer> content) {
        this.content = content;
    }
}