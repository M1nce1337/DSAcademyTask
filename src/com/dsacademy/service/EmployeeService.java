package com.dsacademy.service;

import com.dsacademy.exception.EmployeeNotFoundException;
import com.dsacademy.model.Employee;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    public Employee getEmployeeById(int id, List<Employee> employees) {
        return employees
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден!"));
    }

    public List<Employee> getEmployeesBySalaryGreaterThan(int targetSalary, List<Employee> employees) {
        return employees
                .stream()
                .filter(employee -> employee.getSalary() >= targetSalary)
                .toList();
    }

    public Map<String, Employee> getEmployeeMap(List<Employee> employees) {
        return employees
                .stream()
                .collect(Collectors.toMap(
                        employee -> "id" + employee.getId(),
                        employee -> employee
                ));
    }

}
