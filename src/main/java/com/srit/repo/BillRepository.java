package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {

}
