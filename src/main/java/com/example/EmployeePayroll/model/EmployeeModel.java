package com.example.EmployeePayroll.model;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

@ToString
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;

    public EmployeeModel(Long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public Long getId() {  // ✅ Added this method
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(@Min(value = 1000, message = "Salary must be at least 1000") double salary) {
        this.salary = salary;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
