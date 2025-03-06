package com.example.EmployeePayroll.service;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeModel> getUserById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeModel createUser(EmployeeDTO employeeDTO) {
        EmployeeModel employee = new EmployeeModel(employeeDTO);  // Using constructor
        return employeeRepository.save(employee);
    }

    public Optional<EmployeeModel> updateUser(Long id, EmployeeDTO userDetails) {
        return employeeRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setSalary(userDetails.getSalary());
            return employeeRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
