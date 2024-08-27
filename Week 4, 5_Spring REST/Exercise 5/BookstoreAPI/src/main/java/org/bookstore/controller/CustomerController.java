package org.bookstore.controller;

import org.bookstore.entity.Customer;
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
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        for(Customer customer1 : customers) {
            if(Objects.equals(customer1.getId(), customer.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomers(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "address") String address,
                             @RequestParam(value = "phone") long phone
                             ) {
        Customer customer = new Customer(id, name, address, phone);
        customers.add(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Customer>> getCustomers() {
        if(customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Customers ", String.valueOf(customers.size()));

        return ResponseEntity.ok().headers(headers).body(customers);
    }
}
