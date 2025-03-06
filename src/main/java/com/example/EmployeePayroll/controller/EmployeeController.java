package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.model.EmployeeModel;
import com.example.EmployeePayroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // curl localhost:8080/employeepayrollservice/ -w "\n"
    // http:localhost:8080/employeepayrollservice/findall
    @GetMapping("/")
    public List<EmployeeModel> getAllUsers() {
        return employeeService.getAllUsers();
    }

    // curl localhost:8080/employeepayrollservice/get/1 -w "\n"
    // http:localhost:8080/employeepayrollservice/findbyid/1
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        return employeeService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 2000}'
    // "http://localhost:8080/employeepayrollservice/create" -w "\n"
    // http:localhost:8080/employeepayrollservice/create/post
    @PostMapping("create")
    public EmployeeModel createUser(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createUser(employeeDTO);
    }

    // curl -X PUT -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 3000}'
    // "http://localhost:8080/employeepayrollservice/update/1" -w "\n"
    // http:localhost:8080/employeepayrollservice/update/1
    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @RequestBody EmployeeDTO userDetails) {
        Optional<EmployeeModel> updatedUser = employeeService.updateUser(id, userDetails);
        return updatedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // curl -X DELETE -H "Content-Type: application/json"
    // localhost:8080/employeepayrollservice/delete/1 -w "\n"
    // http:localhost:8080/employeepayrollservice/delete/1
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (employeeService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
