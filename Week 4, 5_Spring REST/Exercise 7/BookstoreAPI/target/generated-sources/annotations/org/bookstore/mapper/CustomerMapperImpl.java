package org.bookstore.mapper;

import javax.annotation.processing.Generated;
import org.bookstore.dto.CustomerDTO;
import org.bookstore.entity.Customer;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T00:16:10+0530",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setAddress( customer.getAddress() );
        customerDTO.setPhone( customer.getPhone() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setAddress( customerDTO.getAddress() );
        customer.setPhone( customerDTO.getPhone() );

        return customer;
    }
}
