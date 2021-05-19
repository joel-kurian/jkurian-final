package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Address;
import com.capgemini.exceptions.AddressExistsException;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService as;
	
	@GetMapping("/all")
	public List<Address> getAllAddresses() {
		return as.getAllAddresses();
	}
	
	@GetMapping("/pin/{pin}")
	public Address getAddressByPin(@PathVariable int pin) 
			throws AddressNotFoundException {
		return as.getAddressByPin(pin);
	}
	
	@PostMapping("/add")
	public Address addAddress(@RequestBody Address a) 
			throws AddressExistsException {
		return as.addAddress(a);
	}
	
	@DeleteMapping("/delete")
	public void deleteAddress(@RequestBody Address a) 
			throws AddressNotFoundException {
		as.deleteAddress(a);
	}
	
	@PutMapping("/update")
	public Address updateAddress(@RequestBody Address a) 
			throws AddressNotFoundException {
		return as.updateAddress(a);
	}
}
