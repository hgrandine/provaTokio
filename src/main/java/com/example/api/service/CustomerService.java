package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	//public HttpStatus createCustumer(String name, ArrayList<String> email) {
	public HttpStatus createCustumer(String name, String email) {	
		try {
			if (name.isEmpty() || email.isEmpty()) {
				return HttpStatus.BAD_REQUEST;
			}
			Customer c = new Customer();
			c.setName(name);
			c.setEmail(email);
			
			repository.save(c);			
			return HttpStatus.ACCEPTED;
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}				
	}

	//public HttpStatus updateCustumer(Long id, String name, ArrayList<String> email) {
	public HttpStatus updateCustumer(Long id, String name, String email) {
		Optional<Customer> cOld = repository.findById(id);
		
		if(cOld.isEmpty()) {
			return HttpStatus.BAD_REQUEST;
		}else {
			try {
				Customer cNew = new Customer();
				cNew.setId(id);
				cNew.setName(name);
				cNew.setEmail(email);
				repository.save(cNew);
				return HttpStatus.ACCEPTED;	
			}catch (Exception e) {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}
			
		}
	}

	public HttpStatus deleteCustumer(Long id) {
		try {
			repository.deleteById(id);
			return HttpStatus.ACCEPTED;	
		}catch (Exception e) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

}
