package com.springdemo.rest;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    private List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/customers/{customerId}")
    private Customer getCustomers(@PathVariable int customerId){
        Customer customer=customerService.getCustomer(customerId);
        if(customer==null){
            throw  new CustomerNotFoundException();
        }
        return customer;
    }
    @PostMapping("/customers")
    private Customer addCustomer(@RequestBody Customer customer){
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }
    @PutMapping("/customers")
    private Customer updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }
    @DeleteMapping("/customers/{customerId}")
    private String deleteCustomers(@PathVariable int customerId){
        Customer customer=customerService.getCustomer(customerId);
        if(customer==null){
            throw  new CustomerNotFoundException();
        }
        customerService.deleteCustomer(customerId);
        return "Deleted customer:"+customerId;
    }
}
