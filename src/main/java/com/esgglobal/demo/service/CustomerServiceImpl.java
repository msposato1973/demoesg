package com.esgglobal.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esgglobal.demo.csv.CSVCustomerReader;
import com.esgglobal.demo.dto.CustomerDto;
import com.esgglobal.demo.model.Customer;
import com.esgglobal.demo.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private  CustomerRepository customerRepository;


	@Override
	public CustomerDto getCustomerByRef(String customerRef) throws Exception {
		List<Customer> list = customerRepository.findByCustomerRef(customerRef);
		return entityToDto(list).get(0) ;
	}

	@Override
	public List<CustomerDto> findAllCustomer() throws Exception {
		List<Customer> list = customerRepository.findAll();
		return entityToDto(list);
	}

	
	private List<CustomerDto> entityToDto(final List<Customer> customers) throws Exception {
		 LOGGER.info("START entityToDto");
		 List<CustomerDto> result = new ArrayList<CustomerDto>();
		 
		 (customers).forEach(x -> {
		        try {
					result.add(mapEntityToDto(x));
				} catch (Exception e) {
					e.printStackTrace();
				}
		 });
		 
		LOGGER.info("END entityToDto");
		return result;
	}  
	
	private CustomerDto mapEntityToDto(final Customer entity)  {
		LOGGER.info("mapToEntity: begin");
		
		return new CustomerDto(
				entity.getCustomerRef(), 
				entity.getCustomerName(), 
				entity.getAddressLine1(),
				entity.getAddressLine2(),
				entity.getTown(),
				entity.getCounty(),
				entity.getCounty(),
				entity.getPostcode()
		   );
		    
	}
	
	private Customer mapDtoToEntity(CustomerDto dto)  {
		LOGGER.info("mapToEntity: begin");
		
		return new Customer(dto.getCustomerRef(), 
				dto.getCustomerName(), 
				dto.getAddressLine1(), 
				dto.getAddressLine2(), 
				dto.getTown(),
				dto.getCounty(), 
				dto.getCountry(), 
				dto.getPostcode()
		);
	}

	@Override
	public List<Customer> saveAll(Collection<Customer> listCustomer) throws Exception {
		
		return customerRepository.saveAll(listCustomer);
	}

	
}
