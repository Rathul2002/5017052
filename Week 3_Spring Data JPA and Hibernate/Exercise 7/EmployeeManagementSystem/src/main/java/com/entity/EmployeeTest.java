package com.entity;
import com.entity.Controller.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class EmployeeTest {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EmployeeTest.class, args);
        EmployeeController employeeController = context.getBean(EmployeeController.class);
        DepartmentController departmentController = context.getBean(DepartmentController.class);
    }
}
