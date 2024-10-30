package edu.miu.cse;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PensionCLI {
    private EmployeeInfoStorage employeeInfoStorage;

    public PensionCLI() {
        this.employeeInfoStorage = new EmployeeInfoStorage();
    }

    public void showMenu() {
        System.out.println("1. View All Employees in JSON");
        System.out.println("2. View Monthly Upcoming Enrollees in JSON");
        System.out.println("3. Enroll Employee in Pension Plan");
        System.out.println("4. View Employee Pension Details");
        System.out.println("0. Exit");
    }

    public void viewAllEmployeesJson() {
        List<Employee> sortedEmployees = employeeInfoStorage.getEmployees().stream()
                .sorted(Comparator.comparing(Employee::getLastName)
                        .thenComparing(Employee::getYearlySalary, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        System.out.println("[");
        for (int i = 0; i < sortedEmployees.size(); i++) {
            System.out.print(sortedEmployees.get(i).toJson());
            if (i < sortedEmployees.size() - 1) {
                System.out.print(",\n");
            }
        }
        System.out.println("\n]");
    }



    public void viewMonthlyUpcomingEnrolleesJson() {
        LocalDate nextMonthStart = LocalDate.now().plusMonths(1).withDayOfMonth(1);
        LocalDate nextMonthEnd = nextMonthStart.withDayOfMonth(nextMonthStart.lengthOfMonth());

        List<Employee> upcomingEnrollees = employeeInfoStorage.getEmployees().stream()
                .filter(e -> e.isEligibleForPensionPlan(nextMonthEnd))
                .sorted(Comparator.comparing(Employee::getEmploymentDate))
                .collect(Collectors.toList());

        // Build JSON manually
        System.out.println("[");
        for (int i = 0; i < upcomingEnrollees.size(); i++) {
            System.out.print(upcomingEnrollees.get(i).toJson());
            if (i < upcomingEnrollees.size() - 1) {
                System.out.print(",\n");
            }
        }
        System.out.println("\n]");
    }

    public void enrollEmployeeInPension() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID to Enroll: ");
        long employeeId = scanner.nextLong();
        scanner.nextLine();  // Consume newline

        Employee employee = employeeInfoStorage.getEmployees().stream()
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        if (employee.getPensionPlan() != null) {
            System.out.println("Employee is already enrolled in a pension plan.");
            return;
        }

        System.out.print("Enter Pension Plan Reference Number: ");
        String referenceNumber = scanner.nextLine();
        System.out.print("Enter Monthly Contribution: ");
        double monthlyContribution = scanner.nextDouble();

        PensionPlan pensionPlan = new PensionPlan(referenceNumber, LocalDate.now(), monthlyContribution);
        employee.enrollInPensionPlan(pensionPlan);

        System.out.println("Employee enrolled successfully in Pension Plan.");
    }

    public void viewEmployeePensionDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        long employeeId = scanner.nextLong();

        Employee employee = employeeInfoStorage.getEmployees().stream()
                .filter(e -> e.getEmployeeId() == employeeId)
                .findFirst()
                .orElse(null);

        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        PensionPlan pensionPlan = employee.getPensionPlan();
        if (pensionPlan == null) {
            System.out.println("Employee is not enrolled in any Pension Plan.");
        } else {
            System.out.println("Pension Plan Reference Number: " + pensionPlan.getPlanReferenceNumber());
            System.out.println("Enrollment Date: " + pensionPlan.getEnrollmentDate());
            System.out.println("Monthly Contribution: $" + pensionPlan.getMonthlyContribution());
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAllEmployeesJson();
                    break;
                case 2:
                    viewMonthlyUpcomingEnrolleesJson();
                    break;
                case 3:
                    enrollEmployeeInPension();
                    break;
                case 4:
                    viewEmployeePensionDetails();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

}
