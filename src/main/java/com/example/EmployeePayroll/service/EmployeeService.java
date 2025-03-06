package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EmployeeService {

    private final List<EmployeeModel> employeeList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // Get all employees
    public List<EmployeeModel> getAllUsers() {
        return new ArrayList<>(employeeList);
    }

    // Get employee by ID
    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
    }

    // Create employee
    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(employeeDTO);
        employee.setId(idCounter.getAndIncrement()); // Assign unique ID
        employeeList.add(employee);
        return employee;
    }

    // Update employee
    public Optional<EmployeeModel> updateUser(Long id, EmployeeDTO userDetails) {
        return getUserById(id).map(employee -> {
            employee.setName(userDetails.getName());
            employee.setSalary(userDetails.getSalary());
            return employee;
        });
    }

    // Delete employee
    public boolean deleteUser(Long id) {
        return employeeList.removeIf(emp -> emp.getId().equals(id));
    }
}
