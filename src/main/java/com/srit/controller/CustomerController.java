package com.srit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.srit.model.Customer;
import com.srit.repo.ItemRepository;
import com.srit.service.CustomerService;
import com.srit.service.UserService;

@Controller
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@Autowired 
	UserService userService;
	
	Logger log = LoggerFactory.getLogger(CustomerController.class);
      
	@Autowired 
	 ItemRepository itemRepository;
	
    @GetMapping("/showCustomer")
	public ModelAndView showCustomer()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addCustomerDetails");
		mv.addObject("customer", new Customer());
		return mv;
	}
    
    /* @PostMapping("/addCustomer")
	public ModelAndView showData(@ModelAttribute Customer customer )
	{   
    	System.out.println("data added");
    	Customer cust =userService.saveCustomer(customer);
		log.info("customerId : {} ", cust.getId());
    	ModelAndView mv = new ModelAndView();
		
		mv.setViewName("select-item");
		mv.addObject("customer_id", customer.getId());
	
		return mv;
	}*/
    @PostMapping("/addCustomer")
	public String showData(@ModelAttribute Customer customer ,Model model)
	{   
      	Customer cust =userService.saveCustomer(customer);
		log.info("customerId : {} ", cust.getId());  
		model.addAttribute("items",itemRepository.findAll() );
		return "bill-item-list";
	}
		
}
