package com.example.EmployeePayroll.controller;
import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EmployeePayroll.Repository.EmployeeRepo;
import java.util.List;

import java.util.Optional;
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepository;

    //curl localhost:8080/employeepayrollservice/ -w "\n"
    //http:localhost:8080/employee/findall
    @GetMapping("/")
    public List<EmployeeModel> getAllUsers() {
        return employeeRepository.findAll();
    }

    //curl localhost:8080/employeepayrollservice/get/1 -w "\n"
    //http:localhost:8080/employee/findallgetbyid/1
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeModel> getUserById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //curl -X POST -H "Content-Type: application/json" -d '{"name": "Lisa","salary":
    //2000}' "http://localhost:8080/employeepayrollservice/create" -w "\n"
    //http:localhost:8080/employee/create/post
    @PostMapping("create")
    public EmployeeModel createUser(@RequestBody EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

    //    curl -X PUT -H "Content-Type: application/json" -d '{"name": "Lisa","salary": 2000}'
//            "http://localhost:8080/employeepayrollservice/update" -w "\n"
    //http:localhost:8080/employee/update/1
    @PutMapping("update/{id}")
    public ResponseEntity<EmployeeModel> updateUser(@PathVariable Long id, @RequestBody EmployeeModel userDetails) {
        Optional<EmployeeModel> optionalUser = employeeRepository.findById(id);

        if (optionalUser.isPresent()) {
            EmployeeModel user = optionalUser.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return ResponseEntity.ok(employeeRepository.save(user));
        }
        return ResponseEntity.notFound().build();
    }

    //http:localhost:8080/employee/delete/1
//• curl -X DELETE -H "Content-Type: application/json"
//localhost:8080/employeepayrollservice/delete/1 -w "\n"
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}