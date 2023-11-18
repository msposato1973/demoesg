package com.esgglobal.demo.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.esgglobal.demo.csv.CSVCustomerReader;
import com.esgglobal.demo.dto.CustomerDto;
import com.esgglobal.demo.model.Customer;
import com.esgglobal.demo.repository.CustomerRepository;
import com.esgglobal.demo.service.CustomerServiceImpl;
import com.esgglobal.demo.utils.WebParams;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
	

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Collection<Customer> listCustomer;

	@MockBean
	public CustomerServiceImpl customerService;

	@MockBean
	private CustomerRepository repository;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
    public void testGetCustomerByRef() throws Exception {
		
		
		Mockito.when(repository.findByCustomerRef("ESG")).thenReturn(buildListfindByCustomerRef());
		Mockito.when(customerService.getCustomerByRef(anyString())).thenReturn(buildCustomerDto());
       
	    MvcResult mvcrs =  mockMvc.perform(get("/api/v1/customer/ESG")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerRef").value("ESG"))
                .andExpect(jsonPath("$.customerName").value("M. Sposato"))
                .andReturn();
	     
		mvcrs.getResponse();
	}
	
	@Test
    public void testGetAllCustomer() throws Exception {
		List<Customer>  customers = new CSVCustomerReader().buildListCustomer();
		List<CustomerDto> customersDto = mapToDto(customers);
		
		Mockito.when(repository.findAll()).thenReturn(customers);
		Mockito.when(customerService.findAllCustomer()).thenReturn(customersDto);
       
	    MvcResult mvcrs =  mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
	     
		mvcrs.getResponse();
	}
			    
     
	
	@Test
    public void testGetAllCustomerByRef_ValidRef_ReturnisNotFound() throws Exception {
		List<Customer> customers = new CSVCustomerReader().buildListCustomer();
		List<CustomerDto> listDto = mapToDto(customers);
	
		Mockito.when(repository.findAll()).thenReturn(customers);
		Mockito.when(customerService.findAllCustomer()).thenReturn(listDto);
	
		Mockito.when(repository.findAll()).thenReturn(customers);
		Mockito.when(customerService.findAllCustomer()).thenReturn(listDto);
		
		mockMvc.perform(get("/customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	 
	@Test
    public void testGetCustomerByRef_ValidRef_ReturnsisNotFound() throws Exception {
        List<Customer> customers = new CSVCustomerReader().buildListCustomer();
		List<CustomerDto> listDto = mapToDto(customers);
	
		Mockito.when(repository.findAll()).thenReturn(customers);
		Mockito.when(customerService.findAllCustomer()).thenReturn(listDto);
		
		mockMvc.perform(get("/customer/xxx")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
	
	@Test
    public void testGetCustomerByRef_NonExistentRef_ReturnsNotFound() throws Exception {
        // Simulare una chiamata GET al controller per un cliente che non esiste
        mockMvc.perform(get(WebParams.URL_NF)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	private List<CustomerDto> mapToDto(Collection<Customer> list) {
		List<CustomerDto> listDto = new ArrayList<>();
		list.forEach((customer) ->  {
			listDto.add(buildCustomerEntity(customer));
		});
		return listDto;
	}

	private CustomerDto buildCustomerEntity(Customer customer) {
		CustomerDto dto = new CustomerDto();
		dto.setAddressLine1(customer.getAddressLine1());
		dto.setAddressLine2(customer.getAddressLine2());
		dto.setTown(customer.getTown());
		dto.setPostcode(customer.getPostcode());
		dto.setCustomerName(customer.getCustomerName());
		dto.setCustomerRef(customer.getCustomerRef());
		return dto;
	}
	
	private CustomerDto buildCustomerDto() {
		CustomerDto dto = new CustomerDto();
		dto.setAddressLine1("Flat 25 Hitherwood Court");
		dto.setAddressLine2("28 Charcot Road");
		dto.setTown("Barnet");
		dto.setPostcode("NW957W");
		dto.setCustomerName("M. Sposato");
		dto.setCustomerRef("ESG");
		return dto;
	}
	
	private List<Customer> buildListfindByCustomerRef() {
		Customer customer = new Customer();
		customer.setAddressLine1("Flat 25 Hitherwood Court");
		customer.setAddressLine2("28 Charcot Road");
		customer.setTown("Barnet");
		customer.setPostcode("NW957W");
		customer.setCustomerName("M. Sposato");
		customer.setCustomerRef("ESG");
		
		return List.of(customer);
	}

}
