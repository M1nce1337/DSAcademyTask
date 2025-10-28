import com.dsacademy.exception.EmployeeNotFoundException;
import com.dsacademy.exception.FileLoadException;
import com.dsacademy.model.Employee;
import com.dsacademy.service.EmployeeService;
import com.dsacademy.service.FileService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileLoadException {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Harrison", "Ford", 150_000));
        employees.add(new Employee(2, "Bob", "Vashington", 70_000));
        employees.add(new Employee(3, "Hayden", "Christensen", 200_000));
        employees.add(new Employee(4, "Natali", "Portman", 150_000));
        employees.add(new Employee(5, "Mark", "Hemmil", 150_000));

        EmployeeService employeeService = new EmployeeService();
        FileService fileService = new FileService();

        try {
            System.out.println(fileService.loadEmployeesFromFile("employeeData1.txt"));
        }
        catch (FileLoadException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            System.out.println(employeeService.getEmployeeById(7, employees));
        }
        catch (EmployeeNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        Employee foundEmployee = employeeService.getEmployeeById(2, employees);
        List<Employee> employeesWithHighSalaries = employeeService.getEmployeesBySalaryGreaterThan(
                                                   foundEmployee.getSalary(), employees);
        List<Employee> employeesFromFile;

        fileService.saveEmployeesToFile(employeesWithHighSalaries, "employeeData.txt");
        employeesFromFile = fileService.loadEmployeesFromFile("employeeData.txt");

        System.out.println(employeeService.getEmployeeMap(employeesFromFile));
    }
}