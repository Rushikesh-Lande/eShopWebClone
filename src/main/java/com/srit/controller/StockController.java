package com.srit.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srit.model.Item;
import com.srit.model.Stock;
import com.srit.repo.ItemRepository;
import com.srit.service.UserService;
@Controller
public class StockController {

	@Autowired
	UserService userService;
	@Autowired
	ItemRepository itemRepo;
	
	@GetMapping("/stockForm")
	public String squForm(Model model) {
		List<Item> items=userService.getItems();
		model.addAttribute("items", items);
		model.addAttribute("stock", new Stock());
		return "stock-form";		
	}
	@PostMapping("/saveStock")
	public String addStock(@ModelAttribute Stock stock,@RequestParam("item")Long item_id  ) {	
		Item itm=itemRepo.findById(item_id).orElse(null);		
		stock.setItem(itm);
		userService.saveStock(stock);
		return "redirect:/stockForm";		
	}
	@GetMapping("/homePage")
	public String homePage() {		
		return "home-page";		
	}
	@GetMapping("/stockList")
	public String getStockList(Model model) {	
		List<Stock> stock=userService.getStock();
		model.addAttribute("stock", stock);
		return "stock-list";		
	}

}
