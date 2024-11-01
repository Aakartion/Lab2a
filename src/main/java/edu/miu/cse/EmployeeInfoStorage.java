package edu.miu.cse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInfoStorage {
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeInfoStorage() {
        PensionPlan danielPension = new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00);
        PensionPlan carlyPension = new PensionPlan("SM2307", LocalDate.of(2019, 11, 04), 1555.50);

        employeeList.add(new Employee(1L, "Daniel", "Agar", LocalDate.of(2018,01,17), 105945.50, danielPension));
        employeeList.add(new Employee(2L, "Benard", "Shaw", LocalDate.of(2019,04,03), 197750.00, null));
        employeeList.add(new Employee(3L, "Carly", "Agar", LocalDate.of(2014,05,16), 842000.75, carlyPension));
        employeeList.add(new Employee(4L, "Wesley", "Schneider", LocalDate.of(2019,9,02), 105945.50, null));
    }

    public List<Employee> getEmployees() {
        return employeeList;
    }
}
