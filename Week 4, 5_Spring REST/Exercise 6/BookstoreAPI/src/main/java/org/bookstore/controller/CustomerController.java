package org.bookstore.controller;

import org.bookstore.entity.Customer;
import org.bookstore.exception.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        for(Customer customer1 : customers) {
            if(Objects.equals(customer1.getId(), customer.getId())) {
                throw new CustomException("Customer already exists");
            }
        }
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Customer with ID " + customer.getId() + " added successfully.");
    }

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomers(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "address") String address,
                             @RequestParam(value = "phone") long phone
                             ) {
        Customer customer = new Customer(id, name, address, phone);
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Customer with ID " + customer.getId() + " added successfully.");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers() {
        if(customers.isEmpty()) {
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            throw new CustomException("Empty customers list");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Customers ", String.valueOf(customers.size()));

        return ResponseEntity.ok().headers(headers).body(customers);
    }
}
