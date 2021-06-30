package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Basket;
import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Product;
import com.tybootcamp.ecomm.entities.Seller;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import com.tybootcamp.ecomm.repositories.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @PostMapping("/")
    public ResponseEntity<?> addProductToBasket(@Valid @RequestBody Customer customer, @Valid @RequestBody Product product) {
        if (customer.getBasket().getContent().containsKey(product)) {
            customer.getBasket().getContent().put(product, customer.getBasket().getContent().get(product) + 1);
        } else {
            customer.getBasket().getContent().put(product, 1);
        }
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}