package com.srit.model; 
 
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date; 
 
@Entity 
public class Bill { 
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id; 
 
    @ManyToOne 
    @JoinColumn(name = "customer_id")
    private Customer customer; 

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date billDate; 
    private double totalAmount; 
    private double discountAmount; 
    private double finalAmount; 
 
    // Other fields, getters, setters, and constructors 
 
    public Long getId() { 
        return id; 
    } 
 
    public void setId(Long id) { 
        this.id = id; 
    } 
 
    public Customer getCustomer() { 
        return customer; 
    } 
 
    public void setCustomer(Customer customer) { 
        this.customer = customer; 
    } 

 
    public Date getBillDate() { 
        return billDate; 
    } 
 
    public void setBillDate(Date billDate) { 
        this.billDate = billDate; 
    } 
 
    public double getTotalAmount() { 
        return totalAmount; 
    } 
 
    public void setTotalAmount(double totalAmount) { 
        this.totalAmount = totalAmount; 
    } 
 
    public double getDiscountAmount() { 
        return discountAmount; 
    } 
 
    public void setDiscountAmount(double discountAmount) { 
        this.discountAmount = discountAmount; 
    } 
 
    public double getFinalAmount() { 
        return finalAmount; 
    } 
 
    public void setFinalAmount(double finalAmount) { 
        this.finalAmount = finalAmount; 
    } 
}
