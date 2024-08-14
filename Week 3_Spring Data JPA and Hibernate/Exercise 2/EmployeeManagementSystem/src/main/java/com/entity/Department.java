package com.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;


    public Department() {

    }
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
