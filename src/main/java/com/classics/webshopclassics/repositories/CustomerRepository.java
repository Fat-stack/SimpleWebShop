package com.classics.webshopclassics.repositories;


import com.classics.webshopclassics.entities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, String> {


}