package com.leahsaenz.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leahsaenz.soloproject.models.Invoice;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
	List<Invoice>findAll();
	List<Invoice>findByStreetAddress(String streetAddress);

}

