package com.capgemini;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.entity.Address;
import com.capgemini.exceptions.AddressExistsException;
import com.capgemini.exceptions.AddressNotFoundException;
import com.capgemini.repo.AddressRepo;
import com.capgemini.service.AddressService;

@SpringBootTest
class AddressTesting {
	
	@Autowired
	AddressService as;
	
	@Autowired
	AddressRepo ar;
	
	@BeforeEach
	@AfterEach
	public void setup() {
		ar.deleteAll();
	}
	
	@Test
	@DisplayName("Test getAllAddresses")
	public void testGetAllAddresses() throws AddressExistsException {
		List<Address> list1 = new ArrayList<Address>();
		
		for (int i = 0; i < 5; i++) {
			Address adr = new Address();
			adr.setCountry("country"+i);
			adr.setState("state"+i);
			adr.setCity("city"+i);
			list1.add(as.addAddress(adr));
		}
		
		List<Address> list2 = as.getAllAddresses();
		for (int i = 0; i < 5; i++) {
			assertThat(list1.get(i)).isEqualToComparingFieldByField(list2.get(i));
		}
	}

	@Test
	@DisplayName("Test addAddress success")
	public void testAddAddressSuccess() throws AddressExistsException {
		Address adr1 = new Address();
		adr1.setCity("city");
		adr1.setCountry("country");
		adr1.setState("state");
		
		adr1 = as.addAddress(adr1);
		
		assertThat(adr1).isNotNull();
	}

	@Test
	@DisplayName("Test addAddress failure")
	public void testAddAddressFailure() {
		Address adr1 = new Address();
		Address adr2 = new Address();
		AddressExistsException ex = null;
		
		try {
			adr1 = as.addAddress(adr1);
			adr2.setPin(adr1.getPin());
			as.addAddress(adr2);
		} catch (AddressExistsException e) {
			ex = e;
		}
		
		assertThat(ex).isInstanceOf(AddressExistsException.class);
	}
	
	@Test
	@DisplayName("Test getAddressByPin success")
	public void testGetAddressByPinSuccess() throws AddressExistsException, AddressNotFoundException {
		Address adr1 = new Address();
		adr1 = as.addAddress(adr1);
		assertThat(adr1).isEqualToComparingFieldByField(as.getAddressByPin(adr1.getPin()));
	}
	
	@Test
	@DisplayName("test getAddressByPin failure")
	public void testGetAddressByPinFailure() throws AddressExistsException {
		Address adr = new Address();
		adr = as.addAddress(adr);
		AddressNotFoundException ex = null;
		try {
			as.getAddressByPin(adr.getPin()+1);
		} catch (AddressNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(AddressNotFoundException.class);
	}
	
	@Test
	@DisplayName("test deleteAddress success")
	public void testDeleteAddressSuccess() throws AddressExistsException, AddressNotFoundException {
		Address adr = as.addAddress(new Address());
		assertThat(as.getAllAddresses().isEmpty()).isFalse();
		
		as.deleteAddress(adr);
		assertThat(as.getAllAddresses().isEmpty()).isTrue();
	}
	
	@Test
	@DisplayName("test deleteAddress failure")
	public void testDeleteAddressFailure() {
		AddressNotFoundException ex = null;
		try {
			as.deleteAddress(new Address());
		} catch (AddressNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(AddressNotFoundException.class);
	}
	
	@Test
	@DisplayName("test updateAddress success")
	public void testUpdateAddressSuccess() throws AddressExistsException, AddressNotFoundException {
		Address adr = as.addAddress(new Address());
		int beginPin = adr.getPin();
		String s1 = adr.getCountry();
		
		adr.setCountry("newCountry");
		adr = as.updateAddress(adr);
		int endPin = adr.getPin();
		String s2 = adr.getCountry();
		
		assertThat(beginPin).isEqualTo(endPin);
		assertThat(s1).isNotEqualTo(s2);
	}
	
	@Test
	@DisplayName("test updateAddress failure")
	public void testUpdateAddressFailure() {
		AddressNotFoundException ex = null;
		try {
			as.updateAddress(new Address());
		} catch (AddressNotFoundException e) {
			ex = e;
		}
		assertThat(ex).isInstanceOf(AddressNotFoundException.class);
	}
}
