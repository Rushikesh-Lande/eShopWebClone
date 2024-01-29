package com.srit.service;

import java.util.List;
import java.util.Optional;

import com.srit.model.Customer;
import com.srit.model.Item;
import com.srit.model.Stock;
import com.srit.model.User;
import com.srit.pojo.BillItem;
import com.srit.pojo.CartItems;

public interface UserService {

	void saveUser(User user);

	void saveStock(Stock stock);
	
	public List<Item> getItems();
	
	public List<Stock> getStock();

	Customer saveCustomer(Customer customer);
	
	List<Item> searchItems(String keyword);
	
	void saveBillItems(BillItem billItem);
	
	public String removeItemById(Long itemId);
	
	public String addItemFromCart(String str);

	
}
