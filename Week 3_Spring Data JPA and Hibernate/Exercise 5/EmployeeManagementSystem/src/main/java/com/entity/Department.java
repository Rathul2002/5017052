package com.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "department")
@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findAllDepartment",
                        query = "select d from Department d "
                ),
                @NamedQuery(
                        name = "findDepartmentById",
                        query = "select d from Department d Where d.id = :n"
                ),
                @NamedQuery(
                        name = "deletebyDepartmentId",
                        query = "delete from Department d where d.id= :n"
                )
        }
)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees;


    public Department() {

    }
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
