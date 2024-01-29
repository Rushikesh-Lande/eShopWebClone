package com.srit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srit.model.Bill;
import com.srit.model.Category;
import com.srit.model.Customer;
import com.srit.pojo.BillItem;
import com.srit.repo.BillRepository;
import com.srit.repo.BillRepository2;
import com.srit.repo.CategoryRepo;
import com.srit.repo.CustomerRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BillRepository billRepository;
	@Autowired
	BillRepository2 billRepository2;

	@Override
	public void saveBrand(Category category) {
		categoryRepo.save(category);
	}

	@Override
	public Customer getCategoryService(Long id) {
		// TODO Auto-generated method stub
		return customerRepo.getById(id);
	}

	@Override
	public Bill saveBill(Bill bill) {

		return billRepository.save(bill);
	}

	@Override
	public Customer getLastCustomer() {

		return customerRepo.findTopByOrderByIdDesc();
	}

	@Override
	@Transactional
	public List<BillItem> getLastBillItem() {
		List<BillItem> allBillItems=billRepository2.findAll();
		billRepository2.deleteAll();;
		return allBillItems;
	}

}
