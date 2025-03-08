package com.example.EmployeePayroll.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class EmployeeDTO {

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]*$", message = "Name must start with an uppercase letter and contain only alphabets and spaces")
    private String name;

    private double salary;

    // Default constructor
    public EmployeeDTO() {}

    // Constructor with all fields
    public EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Constructor with only name
    public EmployeeDTO(String name) {
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
