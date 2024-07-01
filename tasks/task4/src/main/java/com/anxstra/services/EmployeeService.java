package com.anxstra.services;

import com.anxstra.entities.Employee;
import com.anxstra.exceptions.EmployeeNotFoundException;
import com.anxstra.repositories.EmployeeRepository;

import java.util.List;

import static com.anxstra.constants.ExceptionMessageConstants.EMPLOYEE_NOT_FOUND;

public class EmployeeService implements Service<Integer, Employee> {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.getById(id).orElseThrow(() ->
                new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND.formatted(id)));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        employeeRepository.update(employee);
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.deleteById(employee.getId());
    }
}
