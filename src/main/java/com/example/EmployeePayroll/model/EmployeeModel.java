package com.example.EmployeePayroll.model;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Min(value = 1000, message = "Salary must be at least 1000")
    private double salary;

    // Constructor to initialize from DTO
    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }


public  String getName(){
        return name;
    }
    public double getsalary(){
        return salary;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSalary(@Min(value = 1000, message = "Salary must be at least 1000") double salary)
    {
        this.salary = salary;

    }
}