package com.esgglobal.demo;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.esgglobal.demo.csv.CSVCustomerReader;
import com.esgglobal.demo.model.Customer;
import com.esgglobal.demo.repository.CustomerRepository;


@SpringBootApplication
@EnableAutoConfiguration
public class DemoesgApplication {

	private static final Logger logger = LoggerFactory.getLogger(DemoesgApplication.class);
	
	public static void main(String[] args) {
		logger.info("main - DemoesgApplication.run: begin");
		SpringApplication.run(DemoesgApplication.class, args);
	}

	
	@Bean
	CommandLineRunner initializeData(@Autowired CustomerRepository repository ) {
		 logger.info("main - DemoesgApplication.initializeData: begin");
		 
		 return args -> {
			 
			 Collection<Customer> listCustomer =  new CSVCustomerReader().buildListCustomer();
		 	 repository.saveAll(listCustomer);
	     };
	}
	
}
