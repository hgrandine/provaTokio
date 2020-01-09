package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/pg")
	public List<Customer> findAllPg() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@PostMapping("/{name}/{email}")
	public HttpStatus createCustomer(@PathVariable("name") String name, 
			//@PathVariable("email") ArrayList<String> email) {
			@PathVariable("email") String email) {
		return service.createCustumer(name, email);
	}
	
	@PutMapping("/{id}/{name}/{email}")
	public HttpStatus updateCustomer(@PathVariable("id") Long id, @PathVariable("name") String name, 
			//@PathVariable("email") ArrayList<String> email) {
			@PathVariable("email") String email) {
		return service.updateCustumer(id, name, email);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteCustomer(@PathVariable("id") Long id) {
		return service.deleteCustumer(id);
	}

}
