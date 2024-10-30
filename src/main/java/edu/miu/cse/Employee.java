package edu.miu.cse;

import java.time.LocalDate;

public class Employee {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double yearlySalary;
    private PensionPlan pensionPlan;

    public Employee(Long employeeId, String firstName, String lastName, LocalDate employmentDate, double yearlySalary, PensionPlan pensionPlan) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.yearlySalary = yearlySalary;
        this.pensionPlan = pensionPlan;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }


    public String getLastName() {
        return lastName;
    }


    public LocalDate getEmploymentDate() {
        return employmentDate;
    }


    public double getYearlySalary() {
        return yearlySalary;
    }

    public void setYearlySalary(double yearlySalary) {
        this.yearlySalary = yearlySalary;
    }

    public void enrollInPensionPlan(PensionPlan pensionPlan){
        this.pensionPlan = pensionPlan;
    }

    public boolean isEligibleForPensionPlan(LocalDate referenceDate) {
        return pensionPlan == null && employmentDate.plusYears(5).isBefore(referenceDate);
    }

    public String toJson() {
        return String.format(
                "{\"employeeId\": %d, \"firstName\": \"%s\", \"lastName\": \"%s\", \"employmentDate\": \"%s\", \"yearlySalary\": %.2f, \"pensionPlan\": %s}",
                employeeId,
                firstName,
                lastName,
                employmentDate != null ? employmentDate.toString() : "null",
                yearlySalary,
                pensionPlan != null ? pensionPlan.toJson() : "null"
        );
    }
}
