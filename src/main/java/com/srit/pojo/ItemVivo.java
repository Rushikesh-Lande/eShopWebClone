package com.srit.pojo;

public class ItemVivo {
	private Long id;
    private String itemName;
    private String brand;
    private String size;
    private String sizeNumber;
    private String forGender;
    private String color;
    private String designType;
    private double bPrice;
    private double discount;
    private String offers;
	private String offerStartDate;
    private String offerEndDate;
    private String addedBy;
    private Long currentQnt;
    private String imageLink;
    
	public Long getCurrentQnt() {
		return currentQnt;
	}
	public void setCurrentQnt(Long currentQnt) {
		this.currentQnt = currentQnt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSizeNumber() {
		return sizeNumber;
	}
	public void setSizeNumber(String sizeNumber) {
		this.sizeNumber = sizeNumber;
	}
	public String getForGender() {
		return forGender;
	}
	public void setForGender(String forGender) {
		this.forGender = forGender;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDesignType() {
		return designType;
	}
	public void setDesignType(String designType) {
		this.designType = designType;
	}
	public double getbPrice() {
		return bPrice;
	}
	public void setbPrice(double bPrice) {
		this.bPrice = bPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getOffers() {
		return offers;
	}
	public void setOffers(String offers) {
		this.offers = offers;
	}
	public String getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(String offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public String getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(String offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	

}
