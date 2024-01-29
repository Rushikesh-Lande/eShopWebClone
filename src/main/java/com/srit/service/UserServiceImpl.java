package com.srit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srit.model.Customer;
import com.srit.model.Item;
import com.srit.model.Stock;
import com.srit.model.User;
import com.srit.pojo.BillItem;
import com.srit.pojo.CartItems;
import com.srit.repo.BillRepository2;
import com.srit.repo.CartRepository;
import com.srit.repo.CustomerRepo;
import com.srit.repo.ItemRepository;
import com.srit.repo.StockRepository;
import com.srit.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	StockRepository stockRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	BillRepository2 billRepository2;
	@Autowired
	CartRepository cartRepo;

	@Override
	public void saveUser(User user) {
	
		userRepo.save(user);
	}

	@Override
	public void saveStock(Stock stock) {
		
		stockRepo.save(stock);
	}

	@Override
	public List<Item> getItems() {
		
		return itemRepo.findAll();
	}

	@Override
	public List<Stock> getStock() {
		
		return stockRepo.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer)
	{
		return customerRepo.save(customer);
	}

	@Override
	public List<Item> searchItems(String keyword) {
	    return itemRepo.findByItemNameContainingIgnoreCase(keyword);
	}

	@Override
	public void saveBillItems(BillItem billItem) {
		
		billRepository2.save(billItem);
	}
	
	@Override
	public String removeItemById(Long itemId) {
		cartRepo.deleteById(itemId);
		return "Item Removed Successfully";
	}
	
	@Override
	public String addItemFromCart(String str) {
		Item item=itemRepo.findByImageLink(str).orElse(null);
		item.setCurrentQnt(item.getCurrentQnt()+1);
		return "Item added succesfully";		
	}
	

}
