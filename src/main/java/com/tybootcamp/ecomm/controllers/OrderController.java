package com.tybootcamp.ecomm.controllers;

import com.tybootcamp.ecomm.entities.Customer;
import com.tybootcamp.ecomm.entities.Siparis;
import com.tybootcamp.ecomm.repositories.CustomerRepository;
import com.tybootcamp.ecomm.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping ("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/")
    public ResponseEntity<?>  order(@Valid @RequestBody Customer customer){
        Siparis order = new Siparis();
        order.setCustomerId(customer.getId());
        customer.getBasket().getContent().forEach((k,v)->order.getContent().put(k,v));
        orderRepository.save(order);

        customer.clearBasket();
        customerRepository.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
