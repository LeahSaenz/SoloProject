package com.leahsaenz.soloproject.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leahsaenz.soloproject.models.Invoice;
import com.leahsaenz.soloproject.repositories.InvoiceRepository;

@Service
public class InvoiceService {
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	//Adding an Invoice
	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}
	
	//All Invoices
	public List<Invoice>all(){
		return invoiceRepo.findAll();
	}
	
	//Find Invoice by ID
	public Invoice findInvoice(Long id) {
		Optional<Invoice>optionalInvoice = invoiceRepo.findById(id);
		if(optionalInvoice.isPresent()) {
			return optionalInvoice.get();
		} else {
			return null;
		}
	}
	
	//Get one Invoice
	public Invoice getOneInvoice(Long id) {
		Optional<Invoice>invoiceOrNull = invoiceRepo.findById(id);
		if(invoiceOrNull.isPresent()) {
			return invoiceOrNull.get();
		} else {
			return null;
		}
	}
	
	//Update an Invoice
	public Invoice updateInvoice(Invoice updatedInvoice) {
		return invoiceRepo.save(updatedInvoice);
	}
	
	//Delete an Invoice
	public void deleteInvoice(Long id) {
		invoiceRepo.deleteById(id);
	}
	
	//Calculate total service by price by street address
	public Map<String, Double> calculateTotalServicePriceByStreet(List<Invoice>invoices){
		return invoices.stream()
				.collect(Collectors.groupingBy(Invoice::getStreetAddress,
						Collectors.summingDouble(Invoice::getServicePrice)));
	}
	
	//All invoices by street address
	public List<Invoice> findByServiceAddress(String streetAddress){
		return invoiceRepo.findByStreetAddress(streetAddress);
	}

}
