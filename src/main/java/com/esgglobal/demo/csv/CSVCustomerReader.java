package com.esgglobal.demo.csv;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esgglobal.demo.model.Customer;

public class CSVCustomerReader {
	
	private static final Logger logger = LoggerFactory.getLogger(CSVCustomerReader.class);

	
	public List<Customer> buildListCustomer() throws Exception {
		logger.info("main - CSVReader.buildListCustomer: begin");
		 InputStream inputStream = getClass().getClassLoader().getResourceAsStream("customers.csv");
		 
	     
	        Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();
	        List<Customer> listCustomer = new ArrayList<>();
	        lines.forEach((line) ->  {
				try {
					if(!line.startsWith("Customer") && !line.endsWith("Postcode"))
						listCustomer.add(buildCustomerEntity(line.split(";")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		 return listCustomer;
		 
	}
	
	private Customer buildCustomerEntity(String[] array) throws Exception {
		 logger.info("CSVReader.buildCustomerEntity: begin");
		 
		 logger.info("CSVReader.buildCustomerEntity: array " +array);
		 return new Customer(array[0].toString(), array[1].toString(),
							 array[2].toString(), array[3].toString(),
							 array[4].toString(), array[5].toString(),
							 array[6].toString(), array[7].toString()
		);
		 
	}

}
