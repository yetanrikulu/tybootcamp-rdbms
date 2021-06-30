package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Siparis,Long> {
}
