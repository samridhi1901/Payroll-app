package com.example.EmployeePayroll.service;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.Repository.EmployeeRepo;
import java.util.List;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepo employeeRepository;

    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

    // Get user by ID
    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create user
    public EmployeeModel createUser(EmployeeModel user) {
        return employeeRepository.save(user);
    }

    // Update user
    public Optional<EmployeeModel> updateUser(Long id, EmployeeModel userDetails) {
        return employeeRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return employeeRepository.save(user);
        });
    }

    // Delete user
    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}