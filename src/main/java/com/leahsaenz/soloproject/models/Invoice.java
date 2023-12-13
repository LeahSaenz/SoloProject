package com.leahsaenz.soloproject.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	//MAY NEED TO CHANGE THE JOIN COLUMN
	@JoinColumn(name="userId")
	private User user;
	
	@NotEmpty(message = "Street Address is required!")
	@Size(min=3, max=30, message ="Street Address must be between 3 and 30 characters")
	private String streetAddress;
	
	@NotEmpty(message = "Service Date is required!")
	@Size (min=6, message = "Please enter a date in mm/dd/yy format")
	private String serviceDate;
	
	@NotEmpty(message = "Invoice Month/Year is required!")
	@Size(min=5, max=5, message = "Please enter 3 letter month and 2 number year")
	private String invoiceMonthYear;
	
	@NotEmpty(message = "Service is required!")
	@Size(min=5, max=128, message = "Service should be between 5 and 128 characters")
	private String service;
	
	@NotNull(message = "Price is required!")
	@Min(value=1, message = "Price must be a positive number")
	private Integer servicePrice;
	
	//Empty Constructor
	public Invoice() {}

	//Constructor with Fields
	public Invoice(User user,
			@NotEmpty(message = "Street Address is required!") @Size(min = 3, max = 30, message = "Street Address must be between 3 and 30 characters") String streetAddress,
			@NotEmpty(message = "Service Date is required!") String serviceDate,
			@NotEmpty(message = "Invoice Month/Year is required!") @Size(min = 5, max = 5, message = "Please enter 3 letter month and 2 number year") String invoiceMonthYear,
			@NotEmpty(message = "Service is required!") @Size(min = 5, max = 128, message = "Service should be between 5 and 128 characters") String service,
			@NotNull(message = "Price is required!") Integer servicePrice) {
		this.user = user;
		this.streetAddress = streetAddress;
		this.serviceDate = serviceDate;
		this.invoiceMonthYear = invoiceMonthYear;
		this.service = service;
		this.servicePrice = servicePrice;
	}

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getInvoiceMonthYear() {
		return invoiceMonthYear;
	}

	public void setInvoiceMonthYear(String invoiceMonthYear) {
		this.invoiceMonthYear = invoiceMonthYear;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Integer getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(Integer servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	

}
