package com.springboot.employeems.repo;

import com.springboot.employeems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository <Employee,Integer> {
}
