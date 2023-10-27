package com.bank.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);
    public String emailAlreadyExists(CustomerAlreadyExistsException ex){
        logger.info(ex.getMessage());
        return ex.getMessage();
    }
}
