package com.example.EmployeePayroll.Repository;

import com.example.EmployeePayroll.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeModel, Long> {
}