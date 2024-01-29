package com.srit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srit.model.Customer;
import com.srit.repo.CustomerRepo;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepo customerRepo; 
	
	@Override
	public Customer saveCustomer(Customer customer)
	{
		return customerRepo.save(customer);
	}

}
