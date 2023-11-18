package com.esgglobal.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.esgglobal.demo.csv.CSVCustomerReader;
import com.esgglobal.demo.model.Customer;
import com.esgglobal.demo.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceImplTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Collection<Customer> listCustomer;

	@InjectMocks
	public CustomerServiceImpl customerService;

	@Mock
	private CustomerRepository repository;

	@Test
	void tesCostructor() throws Exception {
		assertThat(customerService).isNotNull();
	}

	@Test
	void testCSVCustomerReader() throws Exception {
		listCustomer = new CSVCustomerReader().buildListCustomer();
		assertThat(listCustomer).isNotNull();
	}

	@Test
	void tesFindAllCustomer() throws Exception {
		List<Customer> customers = new CSVCustomerReader().buildListCustomer();
		when(repository.findAll()).thenReturn(customers);
		assertThat(customerService.findAllCustomer()).isNotNull();
	}
	
	@Test
	void tesFindByCustomerRef() throws Exception {
		List<Customer> customers = new CSVCustomerReader().buildListCustomer();
		
		
		Customer customESG = customers.stream()
				  .filter(customer -> "ESG".equalsIgnoreCase(customer.getCustomerRef()))
				  .findFirst()
				  .orElse(null);
		
		assertThat(customESG).isNotNull();
	    when(repository.findByCustomerRef("ESG")).thenReturn(List.of(customESG));
		
		assertThat(customerService.getCustomerByRef("ESG")).isNotNull();
	}

}
