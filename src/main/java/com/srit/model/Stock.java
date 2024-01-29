package com.srit.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Stock {

 
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    
	    @ManyToOne
	    @JoinColumn(name = "item_id")
	    private Item item;
	    private int quantity; 
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date stockAddDate;
	  
	   
		public Date getStockAddDate() {
			return stockAddDate;
		}
		public void setStockAddDate(Date stockAddDate) {
			this.stockAddDate = stockAddDate;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Item getItem() {
			return item;
		}
		public void setItem(Item item) {
			this.item = item;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	
}
