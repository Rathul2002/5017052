package org.bookstore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Bookstore API", version = "1.0", description = "API documentation for the Bookstore application"))
public class BookStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }
}
