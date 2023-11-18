package com.esgglobal.demo.error;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String customerRef) {
	    super("Could not find Customer by customerRef " + customerRef);
	}

}
