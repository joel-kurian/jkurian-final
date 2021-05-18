package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Address;
import com.capgemini.exceptions.AddressExistsException;
import com.capgemini.exceptions.AddressNotFoundException;

public interface AddressService {
	
	List<Address> getAllAddresses();
	
	Address getAddressByPin(int pin) throws AddressNotFoundException;
	
	Address addAddress(Address a) throws AddressExistsException;
	
	void deleteAddress(Address a) throws AddressNotFoundException;
	
	Address updateAddress(Address a) throws AddressNotFoundException;
}
