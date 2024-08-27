package org.bookstore.controller;

import org.bookstore.dto.CustomerDTO;
import org.bookstore.entity.Customer;
import org.bookstore.exception.CustomException;
import org.bookstore.mapper.CustomerMapper;
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
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        for (Customer existingCustomer : customers) {
            if (Objects.equals(existingCustomer.getId(), customer.getId())) {
                throw new CustomException("Customer with ID " + customer.getId() + " already exists.");
            }
        }
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Customer with ID " + customer.getId() + " added successfully.");
    }

    @PostMapping("/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addCustomerWithParams(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "address") String address,
            @RequestParam(value = "phone") long phone
    ) {
        CustomerDTO customerDTO = new CustomerDTO(id, name, address, phone);
        Customer customer = customerMapper.toEntity(customerDTO);
        customers.add(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Customer Added", "true");

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body("Customer with ID " + customer.getId() + " added successfully.");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        if (customers.isEmpty()) {
            throw new CustomException("Empty customers list");
        }
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(customerMapper.toDTO(customer));
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Total Customers", String.valueOf(customerDTOs.size()));

        return ResponseEntity.ok().headers(headers).body(customerDTOs);
    }
}
