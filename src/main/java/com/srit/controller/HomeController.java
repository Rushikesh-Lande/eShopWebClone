package com.srit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.srit.model.Category;
import com.srit.model.Item;
import com.srit.model.User;
import com.srit.pojo.ItemVivo;
import com.srit.repo.ItemRepository;
import com.srit.repo.UserRepository;
import com.srit.service.CategoryService;
import com.srit.service.UserService;

import java.util.List;


@Controller
public class HomeController {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService; // importing service interfaace
	@Autowired
	UserRepository repository;
	HomeController(){
	}

	@GetMapping("/create")
	public String showCreateForm() {
		return "create-item";

	}
	
	@PostMapping("/save")
	public String saveItem(ItemVivo ItemVivo) {
		
		itemRepository.save(convertItem(ItemVivo));
		return "redirect:/create";
	}
	private Item convertItem(ItemVivo ItemVivo) {
		Item item = new Item();
		item.setId(ItemVivo.getId());
		item.setItemName(ItemVivo.getItemName());
		item.setBrand(ItemVivo.getBrand());
		item.setSize(ItemVivo.getSize());
		item.setSizeNumber(ItemVivo.getSizeNumber());
		item.setForGender(ItemVivo.getForGender());
		item.setColor(ItemVivo.getColor());
		item.setDesignType(ItemVivo.getDesignType());
		item.setbPrice(ItemVivo.getbPrice());
		item.setDiscount(ItemVivo.getDiscount());
		item.setOffers(ItemVivo.getOffers());
		item.setOfferStartDate(getDate(ItemVivo.getOfferStartDate(), "yyyy-MM-dd"));
		item.setOfferEndDate(getDate(ItemVivo.getOfferEndDate(), "yyyy-MM-dd"));
		item.setAddedBy(ItemVivo.getAddedBy());
		item.setCurrentQnt(ItemVivo.getCurrentQnt());
		item.setImageLink(ItemVivo.getImageLink());
		
		return item;
	}
	public Date getDate(String strDate, String pattern) {
		if (pattern == null)
			pattern = "dd-MM-yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
		try {
			return formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/list")
	public String listItems(Model model) {
		model.addAttribute("items", itemRepository.findAll());
		// model.addAttribute("msg", "sign up successfully!!");
		return "list-items";
	}

	@GetMapping("/details/{id}")
	public String itemDetails(@Autowired Long id, Model model) {
		Item item = itemRepository.findById(id).orElse(null);
		if (item != null) {
			model.addAttribute("item", item);
			return "item-details";
		}
		return "redirect:/list";
	}
	
	@RequestMapping("/insert")
	public String showHome() {
		User user = new User();
		user.setName("USER");
		user.setPassword("user");
		user.setEmployeeid("USER");
		repository.save(user);
		System.out.println("show home");
		return "home";

	}

	@GetMapping("/uSignIn")
	public String getUse(Model model) {
		model.addAttribute("user", new User());
		return "uSignIn";
	}

	@PostMapping("/submitData")
	public String updateUser(@ModelAttribute User user, Model model) {
		String enteredName =user.getName();
		user.setEmployeeid(enteredName);
		userService.saveUser(user);
		return "redirect:/uSignIn";
	}
	
	
	@GetMapping("/showHome")
	public ModelAndView display() 
	{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("category");
		mv.addObject("category", new Category());
		return mv;
    }
	
	@PostMapping("/saveCategory")
	public ModelAndView showData(@ModelAttribute Category category )
	{   
		categoryService.saveBrand(category);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("category");
		mv.addObject("category", new Category());
		return mv;
	}
	@GetMapping("/search")
	public String searchItems(@RequestParam("keyword") String keyword, Model model) {
	    List <Item> searchResults = userService.searchItems(keyword);
	    model.addAttribute("items", searchResults);
	    return "search-results"; 
	}
}
