package com.leahsaenz.soloproject.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
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
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Invoice>invoices;
	
	@NotEmpty(message = "Full Name is required!")
	@Size(min=3, max=30, message ="Full Name must be between 3 and 30 characters")
	private String fullName;
	
	@NotNull(message = "Lawn Maintenance Price is required!")
	@Min(value=1, message = "Price must be a positive number")
	private Integer lawnMaintenancePrice;
	
	@NotEmpty(message = "Street Address is required!")
	@Size(min=3, max=30, message ="Street Address must be between 3 and 30 characters")
	private String streetAddress;
	
	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email")
	private String email;
	
	@NotEmpty(message = "Password is required!")
	@Size(min=8, max=128, message = "Password must be between 8 and 128 characters")
	@Column(columnDefinition = "TEXT")
	private String password;
	
	@NotNull (message = "Is a Customer is a required field")
	private Boolean isCustomer;
	
	@NotNull (message = "Is Admin is a required field")
	private Boolean isAdmin;
	
	@Transient
	//@NotEmpty(message = "Confirm password is required!")
	//@Size(min=8, max=128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirmPW;
	
	//Empty Constructor
	public User() {}

	//Constructor with fields
	public User(List<Invoice> invoices,
			@NotEmpty(message = "Full Name is required!") @Size(min = 3, max = 30, message = "Full Name must be between 3 and 30 characters") String fullName,
			@NotNull(message = "Lawn Maintenance Price is required!") @Min(value = 1, message = "Price must be a positive number") Integer lawnMaintenancePrice,
			@NotEmpty(message = "Street Address is required!") @Size(min = 3, max = 30, message = "Street Address must be between 3 and 30 characters") String streetAddress,
			@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email") String email,
			@NotEmpty(message = "Password is required!") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotNull(message = "Is a Customer is a required field") Boolean isCustomer,
			@NotNull(message = "Is Admin is a required field") Boolean isAdmin,
			@NotEmpty(message = "Confirm password is required!") @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirmPW) {
		this.invoices = invoices;
		this.fullName = fullName;
		this.lawnMaintenancePrice = lawnMaintenancePrice;
		this.streetAddress = streetAddress;
		this.email = email;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isAdmin = isAdmin;
		this.confirmPW = confirmPW;
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getLawnMaintenancePrice() {
		return lawnMaintenancePrice;
	}

	public void setLawnMaintenancePrice(Integer lawnMaintenancePrice) {
		this.lawnMaintenancePrice = lawnMaintenancePrice;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getConfirmPW() {
		return confirmPW;
	}

	public void setConfirmPW(String confirmPW) {
		this.confirmPW = confirmPW;
	}
			
	
}
