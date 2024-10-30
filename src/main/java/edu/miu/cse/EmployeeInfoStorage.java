package edu.miu.cse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInfoStorage {
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeInfoStorage(List<Employee> employeeList) {
        employeeList.add(new Employee(1L, "Daniel", "Agar", LocalDate.of(2018,01,17), 105945.50));
        employeeList.add(new Employee(2L, "Benard", "Shaw", LocalDate.of(2019,04,03), 197750.00));
        employeeList.add(new Employee(3L, "Carly", "Agar", LocalDate.of(2014,05,16), 842000.75));
        employeeList.add(new Employee(4L, "Wesley", "Schneider", LocalDate.of(2019,9,02), 105945.50));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
