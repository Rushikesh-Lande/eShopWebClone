package com.srit.service;

import java.util.List;

import com.srit.model.Bill;
import com.srit.model.Category;
import com.srit.model.Customer;
import com.srit.pojo.BillItem;

public interface CategoryService {

	void saveBrand(Category category);

	Customer getCategoryService(Long id);

	Bill saveBill(Bill bill);

	Customer getLastCustomer();

	List<BillItem> getLastBillItem();
}
