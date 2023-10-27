package com.bank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> findAll(){
        return customerService.findAllCustomers();
    }

    @PostMapping("/")
    public String saveCustomer(@RequestBody Customer customer) throws CustomerAlreadyExistsException {
        return customerService.saveUser(customer);
    }

    @GetMapping("/email")
    public Customer getByEmail(@RequestParam String email){
        return customerService.getByEmail(email);
    }
}
