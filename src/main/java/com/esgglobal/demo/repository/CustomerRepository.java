package com.esgglobal.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
import com.esgglobal.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = "SELECT * FROM customer c WHERE c.customer_ref = :customerRef", nativeQuery = true)
	public List<Customer> findByCustomerRef(String customerRef);

}
