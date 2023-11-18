package com.esgglobal.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esgglobal.demo.dto.CustomerDto;
import com.esgglobal.demo.service.CustomerService;
import com.esgglobal.demo.utils.WebParams;

@CrossOrigin
@RestController
@RequestMapping(WebParams.ROOT)
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping(path=WebParams.CUSROMER, produces = "application/json")
	/***
	 * 
	 * @param customerRef
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<CustomerDto> getCustomerByRef(@PathVariable("customerRef") String customerRef)
			throws Exception {
		LOGGER.info("START - getCustomerByRef {..}");
		try {
			CustomerDto customer = customerService.getCustomerByRef(customerRef);
			return buildResponse(customer, customerRef);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path=WebParams.ALL, produces = "application/json")
	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<?> findAll() throws Exception {
		LOGGER.info("START - findAll {..}");
		try {
			List<CustomerDto> listCustomer = customerService.findAllCustomer();

			return (!listCustomer.isEmpty()) ? buildResponseEntity(listCustomer)
					: new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/***
	 * 
	 * @param <T>
	 * @param response
	 * @param customerRef
	 * @return
	 */
	private <T> ResponseEntity<CustomerDto> buildResponse(CustomerDto response, String customerRef) {

		return (response != null) ? new ResponseEntity<>(response, HttpStatus.OK)
				: new ResponseEntity<>(new CustomerDto(), HttpStatus.NOT_FOUND);
	}

	/***
	 * 
	 * @param <T>
	 * @param antityDTO
	 * @return
	 */
	private <T> ResponseEntity<?> buildResponseEntity(List<T> antityDTO) {
		if (antityDTO.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		return ResponseEntity.ok(antityDTO);
	}

}
