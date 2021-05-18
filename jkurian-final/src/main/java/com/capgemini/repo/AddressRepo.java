package com.capgemini.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
