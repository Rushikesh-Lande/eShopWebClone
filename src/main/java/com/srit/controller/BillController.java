package com.srit.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.srit.model.Bill;
import com.srit.model.Customer;
import com.srit.model.Item;
import com.srit.pojo.BillItem;
import com.srit.pojo.CartItems;
import com.srit.repo.BillRepository2;
import com.srit.repo.CartRepository;
import com.srit.repo.ItemRepository;
import com.srit.service.CategoryService;
import com.srit.service.UserService;

@Controller
public class BillController {

	@Autowired
	UserService userService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	BillRepository2 billRepository2;
	@Autowired
	CartRepository cartRepo;

	@GetMapping("/generate-bill-form")
	public String showGenerateBillForm(Model model) {

		Customer customer = categoryService.getLastCustomer();
		List<BillItem> billItem = categoryService.getLastBillItem();
		model.addAttribute("billItem", billItem);
		model.addAttribute("customer", customer);
		return "bill-form";
	}

	@PostMapping("/generate-bill")
	public String saveBill(@ModelAttribute Bill bill) {

		categoryService.saveBill(bill);
		return "bill-generated";
	}

	@GetMapping("/search2")
	public String search2Items(@RequestParam("keyword") String keyword, Model model) {
		List<Item> searchResults = userService.searchItems(keyword);
		model.addAttribute("items", searchResults);
		return "save-selected-item";
	}

	@PostMapping("/addToCart")
	public String addToCartItem(@RequestParam Long itemId, Model model) {
		
		

		Optional<Item> itemOptional = itemRepo.findById(itemId);
		
		if(itemOptional.isPresent()) {
			Item item=itemOptional.get();
			
			if(item.getCurrentQnt()>0  ) {
				
				item.setCurrentQnt(item.getCurrentQnt()-1);
				itemRepo.save(item);
				Long qnt=(long) 1;
				CartItems cartItems = new CartItems();
				cartItems.setItemName(item.getItemName());
				cartItems.setBrand(item.getBrand());
				cartItems.setSize(item.getSize());
				cartItems.setSizeNumber(item.getSizeNumber());
				cartItems.setForGender(item.getForGender());
				cartItems.setColor(item.getColor());
				cartItems.setDesignType(item.getDesignType());
				cartItems.setbPrice(item.getbPrice());
				cartItems.setDiscount(item.getDiscount());
				cartItems.setOffers(item.getOffers());
				cartItems.setOfferStartDate(item.getOfferStartDate());
				cartItems.setOfferEndDate(item.getOfferEndDate());
				cartItems.setAddedBy(item.getAddedBy());
				cartItems.setCurrentQnt(qnt);
				cartItems.setImageLink(item.getImageLink());
				
				//if the item is already present in cart list 
				String iLink= item.getImageLink();
				CartItems cartItem= cartRepo.findByImageLink(iLink).orElse(null);
				if(cartItem==null) {
					cartRepo.save(cartItems);
				}else {
					
				String str=cartItem.getImageLink();
				 
				if(iLink.equals(str)) {
					return "redirect:/cart-list";
				}
				else {
					cartRepo.save(cartItems);
				
			         }
				}
								
			}else {
				throw new RuntimeException("Item quantity is zero, can not add to the cart");
			}
		}else {
			throw new RuntimeException("Item not found in the database");
		}

		
		
		return "redirect:/cart-list";
	}

	@GetMapping("/modern-list")
	public String mordenList(Model model) {
		model.addAttribute("itemList", itemRepo.findAll());
		return "item-modern-list";
	}

	@PostMapping("/saveBillDetails")
	public String saveBilldetails() {

		return "bill-generated";
	}

	@GetMapping("/item")
	public String itemDetailsItemID(@RequestParam(name = "id") Long itemId, Model model) {
		Optional<Item> itemOptional = itemRepo.findById(itemId);
	        model.addAttribute("itemDetails", itemOptional);
	    
		return "item-details";
	}
	@GetMapping("/item-details2")
	public String itemDetailsImageLink(@RequestParam String imageLink, Model model) {
		
		System.out.println("0000000000000000000000000000000000000000000"+imageLink+"0000000000000000000000000000000000000000000");
		Optional<Item> itemOptional = itemRepo.findByImageLink(imageLink);
	        model.addAttribute("itemDetails", itemOptional);
		return "item-details";
	}
	

	@GetMapping("/cart-list")
	public String cartList(Model model/*, @RequestParam(name = "imageLink") String imageLink*/) {

		List<CartItems> item = cartRepo.findAll();
		/*Optional<Item> item2 = itemRepo.findByImageLink(imageLink);*/
		model.addAttribute("cartList", item);
		/*itemRepo.findByItemNameContainingIgnoreCase(null);*/

		return "cart";
	}
	
	@GetMapping("/remove-from-cart")
	public String removeCartItem(@RequestParam("id") Long itemId ) {

		CartItems cartItem=cartRepo.findById(itemId).orElse(null);
		String str=cartItem.getImageLink();
		userService.addItemFromCart(str);
		userService.removeItemById(itemId);
		return "redirect:/cart-list";
	}
	
	@PostMapping("/update-quantity")
	public ResponseEntity<String> updateItemQuantity(@RequestParam String itemId ,@RequestParam String operation	){
				
		
		System.out.println(itemId);
	/*try {	
		Optional <CartItems> oplcartItem=cartRepo.findById(itemId);
		if(oplcartItem.isPresent()) {
			CartItems cartItem=oplcartItem.get();
			String str=cartItem.getImageLink();
			Optional <Item> oplItem= itemRepo.findByImageLink(str);
			if(oplItem.isPresent()) {
				
				Item item=oplItem.get();
				
			if("increment".equals(operation)) {
				
				if(item.getCurrentQnt()>0) {
					item.setCurrentQnt(item.getCurrentQnt()-1);				
					cartItem.setCurrentQnt(cartItem.getCurrentQnt()+1);				
				}else {System.out.println("Item quantity is zero in Item List can not do increment");}
								
			}else if("decrement".equals(operation)){
				
				if(cartItem.getCurrentQnt()>0) {
					item.setCurrentQnt(item.getCurrentQnt()+1);					
					cartItem.setCurrentQnt(cartItem.getCurrentQnt()-1);					
				}else {
					System.out.println("Item quantity is zero in Cart List can not do increment");
				}	
				
				itemRepo.save(item);
				cartRepo.save(cartItem);
				
				 return ResponseEntity.ok("Quantity updated successfully");
			}

			}else {
				return ResponseEntity.notFound().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
		}
	  catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Failed to update quantity");
	    }*/
		
		return ResponseEntity.ok("Quantity incremented successfully");
	}
	
	 // Define a method to handle increment requests
    @PostMapping("/increment")
    public ResponseEntity<String> incrementQuantity(@RequestParam Long itemId) {
    	
    	System.out.println("000000000000000000000000..."+itemId+"...000000000000000000");
        // Perform your increment operation here using the itemId
        // Update the cart item's quantity
        // You can return a success message or updated data if needed
        return ResponseEntity.ok("Quantity incremented successfully");
    }

}
