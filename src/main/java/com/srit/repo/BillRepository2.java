package com.srit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srit.model.Customer;
import com.srit.pojo.BillItem;

public interface BillRepository2 extends JpaRepository<BillItem, Long> {

	Customer findTopByOrderByIdDesc();
	
}
