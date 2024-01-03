package com.springboot.employeems.service;

import com.springboot.employeems.dto.EmployeeDTO;
import com.springboot.employeems.entity.Employee;
import com.springboot.employeems.repo.EmployeeRepo;
import com.springboot.employeems.util.varList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee (EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            return varList.RSP_DUPLICATED;
        }
        else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return varList.RSP_SUCCESS;
        }
    }

    public String updateEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO,Employee.class));
            return varList.RSP_SUCCESS;

        }
        else {
            return varList.RSP_NO_DATA_FOUND;

        }
    }
    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList,new TypeToken<ArrayList<EmployeeDTO>>(){

        }.getType());
    }

    public EmployeeDTO searchEmployee(int empId){
        if(employeeRepo.existsById(empId)){
            Employee employee = employeeRepo.findById(empId).orElse(null);
            return modelMapper.map(employee,EmployeeDTO.class);}
        else{
            return  null;
        }

    }
    public String deleteEmployee(int empId){
        if(employeeRepo.existsById(empId)){
            employeeRepo.deleteById(empId);
            return varList.RSP_SUCCESS;
        }
        else{
            return varList.RSP_NO_DATA_FOUND;
        }
    }
}
