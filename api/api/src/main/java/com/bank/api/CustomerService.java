package com.bank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    public List<Customer> findAllCustomers(){
        return customerRepo.findAll();
    }

    public Customer getByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    public String saveUser(Customer customer) throws CustomerAlreadyExistsException {
        if(customerRepo.findByEmail(customer.getEmail())!=null){
            customerRepo.save(customer);
            return "Customer saved successfully";
        }
        throw new CustomerAlreadyExistsException(customer.getEmail());
    }
}
