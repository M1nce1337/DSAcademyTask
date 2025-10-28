package com.dsacademy.service;

import com.dsacademy.exception.FileLoadException;
import com.dsacademy.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public void saveEmployeesToFile(List<Employee> employees, String filename) {

        try(FileWriter writer = new FileWriter(filename, false)) {
            for (Employee employee: employees) {
                writer.write(employee.getId() + "," + employee.getFirstName() + "," +
                                 employee.getLastName() + "," + employee.getSalary() + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> loadEmployeesFromFile(String filename) throws FileLoadException {
        List<Employee> employees = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] employeeData = s.split(",");

                try {
                    int id = Integer.parseInt(employeeData[0]);
                    String firstName = employeeData[1];
                    String lastName = employeeData[2];
                    int employeeSalary = Integer.parseInt(employeeData[3]);

                    Employee employee = new Employee(id, firstName, lastName, employeeSalary);
                    employees.add(employee);
                }
                catch (Exception exception) {
                    System.err.println("Ошибка при чтении строки: " + exception.getMessage());
                }
            }
        }
        catch (IOException e) {
            throw new FileLoadException("Не удалось загрузить файл " + filename + ": файл не найден или повреждён.");
        }

        return employees;
    }
}
