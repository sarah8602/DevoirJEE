package com.myHR.api_sb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHR.api_sb.model.Employee;
import com.myHR.api_sb.repository.EmployeeRepository;

import lombok.Data;

//@Service : tout comme l’annotation @Repository, c’est une spécialisation de @Component. 
//Son rôle est donc le même, mais son nom a une valeur sémantique pour ceux qui lisent le code.

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployee(final Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

}