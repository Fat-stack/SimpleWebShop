package com.classics.webshopclassics.repositories;


import com.classics.webshopclassics.entities.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders, Long> {




    List<Orders> findTop1ByOrderByOrdernumberDesc();
}