package com.esgglobal.demo.error;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
	 
	private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientErrorException(HttpClientErrorException httptErrorException) {
        if (httptErrorException.getRawStatusCode() == 404) {
            return new ResponseEntity<Object>(httptErrorException.getStatusText(), HttpStatus.NOT_FOUND);
        } else if (httptErrorException.getRawStatusCode() == 400) {
        	return new ResponseEntity<Object>(httptErrorException.getStatusText(), HttpStatus.BAD_REQUEST);
		}
        
        logger.error("ControllerExceptionHandler:httptErrorException: Exception Occurred : {}",
        		httptErrorException);
        return new ResponseEntity<Object>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> employeeNotFoundHandler(CustomerNotFoundException ex) {
	    return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	 

}
