package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Customer findTopByOrderByIdDesc();
}
