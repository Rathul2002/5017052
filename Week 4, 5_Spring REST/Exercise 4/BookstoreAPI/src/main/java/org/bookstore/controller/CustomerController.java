package org.bookstore.controller;

import org.bookstore.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private List<Customer> customers = new ArrayList<Customer>();

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
    }

    @PostMapping("/registrations")
    public void addCustomers(@RequestParam(value = "id",required = true) int id,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "phone", required = false) long phone
                             ) {
        Customer customer = new Customer(id, name, address, phone);
        customers.add(customer);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }
}
