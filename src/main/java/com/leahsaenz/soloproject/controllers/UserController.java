package com.leahsaenz.soloproject.controllers;

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


import com.leahsaenz.soloproject.models.LoginUser;
import com.leahsaenz.soloproject.models.User;
import com.leahsaenz.soloproject.services.InvoiceService;
import com.leahsaenz.soloproject.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService userServ;
	
	@Autowired
	InvoiceService invoiceServ;
	
	@GetMapping("/")
	public String loginRegPage(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "login.jsp";
	}
	
	@PostMapping("/register")
	public String processRegistration(@Valid @ModelAttribute("newUser") User newUser,
			BindingResult result, Model model, HttpSession session) {
		User theNewUser = userServ.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "login.jsp";
		} else {
			session.setAttribute("userId", theNewUser.getId());
			return "redirect:/dashboard";
		}
	}
		
		@PostMapping("/login")
		public String processLogin(@Valid @ModelAttribute("newLogin") LoginUser loginUser,
				BindingResult result, Model model, HttpSession session) {
			User foundUser = userServ.login(loginUser, result);
			if(result.hasErrors()) {
				model.addAttribute("newUser", new User());
				return "login.jsp";
			}
			if(foundUser.getIsAdmin()) {
				session.setAttribute("userId", foundUser.getId());
				return "redirect:/admin/dashboard";
				} else {
			session.setAttribute("userId", foundUser.getId());
			return "redirect:/dashboard";
			}
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			session.invalidate();
			return "redirect:/";
		}
		
		//Adding a new Customer
		@GetMapping("/add/customer")
		public String addCustomer(@ModelAttribute("user") User user, Model model, HttpSession session) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/admin/dashboard";
			}
			Long userId = (Long)session.getAttribute("userId");
			model.addAttribute("loggedUser", userServ.findById(userId));
			return "addCustomer.jsp";
		}
		
		@PostMapping("/add/customer")
		public String createCustomer(HttpSession session, @Valid @ModelAttribute("user") User user,
				BindingResult result, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/admin/dashboard";
			}
			Long userId = (Long)session.getAttribute("userId");
			
			if (result.hasErrors()) {
				model.addAttribute("loggedUser", userServ.findById(userId));
				return "addCustomer.jsp";
			} else {
				userServ.register(user, result);
				return "redirect:/admin/dashboard";
			}
		}
		//Viewing a customer details
		@GetMapping("/customer/{id}")
		public String viewCustomer(HttpSession session, @PathVariable("id") Long id, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long)session.getAttribute("userId");
			model.addAttribute("loggedUser", userServ.findById(userId));
			
			User foundCustomer = userServ.findUser(id);
			model.addAttribute("thisCustomer", foundCustomer);
			return "viewCustomer.jsp";
		}
		
		//Editing a Customer
		@GetMapping("/customer/{id}/edit")
		public String editCustomer(HttpSession session, @PathVariable("id") Long id, Model model) {
			if (session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long)session.getAttribute("userId");
			model.addAttribute("loggedUser", userServ.findById(userId));
			User foundCustomer = userServ.getOneUser(id);
			model.addAttribute("thisCustomer", foundCustomer);
			return "editCustomer.jsp";
		}
		
		@PutMapping("/customer/{id}/update")
		public String editCustomerInDb(HttpSession session, @Valid @ModelAttribute("thisCustomer") User thisCustomer, 
				BindingResult result, Model model) {
			if (session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long)session.getAttribute("userId");
			model.addAttribute("loggedUser", userServ.findById(userId));
			
			if (result.hasErrors()) {
				System.out.println(result);
				return "editCustomer.jsp";
			} else {
				User editedCustomer = userServ.updateUser(thisCustomer);
				return "redirect:/admin/dashboard";
			}
		}
		
		//Deleting a Customer
		@DeleteMapping("/delete/customer/{id}")
		public String deleteCustomer(HttpSession session, @PathVariable("id")Long id, Model model) {
			if(session.getAttribute("userId") == null) {
				return "redirect:/";
			}
			Long userId = (Long)session.getAttribute("userId");
			model.addAttribute("loggedUser", userServ.findById(userId));
			
			userServ.deleteUser(id);
			return "redirect:/admin/dashboard";
		}
}


