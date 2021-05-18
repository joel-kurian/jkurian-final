package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.entity.Address;
import com.capgemini.exceptions.AddressExistsException;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.repo.AddressRepo;

public class AddressServiceimpl implements AddressService {
	
	@Autowired
	AddressRepo ar;

	@Override
	public List<Address> getAllAddresses() {
		return ar.findAll();
	}

	@Override
	public Address getAddressByPin(int pin) throws AddressNotFoundException {
		return ar.findById(pin).orElseThrow(
				() -> new AddressNotFoundException("Address not found"));
	}

	@Override
	public Address addAddress(Address a) throws AddressExistsException {
		Optional<Address> adr = ar.findById(a.getPin());
		if (adr.isPresent())
			throw new AddressExistsException("Address already exists");
		return ar.save(a);
	}

	@Override
	public void deleteAddress(Address a) throws AddressNotFoundException {
		ar.findById(a.getPin()).orElseThrow(
				() -> new AddressNotFoundException("Address not found"));
		ar.delete(a);
	}

	@Override
	public Address updateAddress(Address a) throws AddressNotFoundException {
		Address adr = ar.findById(a.getPin()).orElseThrow(
				() -> new AddressNotFoundException("Address not found"));
		adr.setCity(a.getCity());
		adr.setCountry(a.getCountry());
		adr.setState(a.getState());
		return ar.save(adr);
	}

}
