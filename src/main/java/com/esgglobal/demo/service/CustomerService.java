package com.esgglobal.demo.service;

import java.util.Collection;
import java.util.List;

import com.esgglobal.demo.dto.CustomerDto;
import com.esgglobal.demo.model.Customer;


public interface CustomerService {

	public CustomerDto getCustomerByRef(String customerRef) throws Exception;
	
	public List<CustomerDto> findAllCustomer() throws Exception;
	
	public List<Customer> saveAll(Collection<Customer> listCustomer) throws Exception;
}
