package com.leahsaenz.soloproject.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.leahsaenz.soloproject.models.Invoice;
import com.leahsaenz.soloproject.services.InvoiceService;
import com.leahsaenz.soloproject.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class InvoiceController {
	@Autowired
	private UserService userServ;
	
	@Autowired
	private InvoiceService invoiceServ;
	
	//Viewing the Dashboard
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		//Talk to service and grab user by saved ID in session
		//Save in model so JSP file can access it
		model.addAttribute("loggedUser", userServ.findById(userId));
		model.addAttribute("user", userServ.allUsers());
		model.addAttribute("invoice", invoiceServ.all());
		return "dashboard.jsp";
	}
	
	//Viewing the Admin Dashboard
	@GetMapping("/admin/dashboard")
	public String adminDashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long)session.getAttribute("userId");
		//Talk to service and grab user by saved ID in session
		//Save in model so JSP file can access it
		model.addAttribute("loggedUser", userServ.findById(userId));
		model.addAttribute("user", userServ.allUsers());
		model.addAttribute("invoice", invoiceServ.all());
		// Controller method
	
		    List<Invoice> invoices = invoiceServ.all();// Retrieve your list of invoices from the backend
		    //Get distinct street address
		    List<String> distinctStreetAddresses = invoices.stream()
		    		.map(Invoice::getStreetAddress)
		    		.distinct()
		    		.collect(Collectors.toList());
		    // Group invoices by street address and calculate total service price
		    Map<String, Double> totalServicePriceByStreet =invoiceServ.calculateTotalServicePriceByStreet(invoices);

		    model.addAttribute("distinctStreetAddresses", distinctStreetAddresses);
		    model.addAttribute("totalServicePriceByStreet", totalServicePriceByStreet);
		    model.addAttribute("invoices", invoices);
		
		return "adminDashboard.jsp";
	}

	
	//Adding a new Invoice
	@GetMapping("/create/invoice")
	public String addInvoice(@ModelAttribute("invoice") Invoice invoice, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		model.addAttribute("loggedUser", userServ.findById(userId));
		return "createInvoice.jsp";
}
	
	@PostMapping("/create/invoice")
	public String createInvoice(HttpSession session, @Valid @ModelAttribute("invoice") Invoice invoice,
			BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		
		if(result.hasErrors()) {
			model.addAttribute("loggedUser", userServ.findById(userId));
			return "createInvoice.jsp";
		} else {
			invoiceServ.addInvoice(invoice);
			return "redirect:/admin/dashboard";
		}		
	}
	
	@GetMapping("/service/view/{streetAddress}")
	public String viewInvoice(HttpSession session, @PathVariable("streetAddress") String streetAddress, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		model.addAttribute("loggedUser", userServ.findById(userId));
		model.addAttribute("user", userServ.allUsers());
		List<Invoice> invoices = invoiceServ.findByServiceAddress(streetAddress);
		model.addAttribute("invoices", invoices);
		return "viewInvoices.jsp";
	}
	
	@GetMapping("/service/{id}/edit")
	public String editInvoice(HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		model.addAttribute("loggedUser", userServ.findById(userId));
		Invoice foundInvoice = invoiceServ.getOneInvoice(id);
		model.addAttribute("thisInvoice", foundInvoice);
		return "editInvoice.jsp";
	}
	
	@PutMapping("/service/{id}/update")
	public String editInvoiceInDb (HttpSession session, @Valid @ModelAttribute("thisInvoice") Invoice thisInvoice,
			BindingResult result, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		model.addAttribute("loggedUser", userServ.findById(userId));
		
		if(result.hasErrors()) {
			return "editInvoice.jsp";
		} else {
			Invoice editedInvoice = invoiceServ.updateInvoice(thisInvoice);
			return "redirect:/admin/dashboard";
		}
	}
	
	@DeleteMapping("delete/service/{id}")
	public String deleteInvoice(HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("userId");
		model.addAttribute("loggedUser", userServ.findById(userId));
		
		invoiceServ.deleteInvoice(id);
		return "redirect:/admin/dashboard";
	}

}
