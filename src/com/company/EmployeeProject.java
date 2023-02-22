package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeProject {
    private Employee employee;
    private  Project project;
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();


    public EmployeeProject(Employee employee, Project project, LocalDate startDate, LocalDate endDate) {
        this.employee = employee;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;

    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return "EmployeeProject{" +
                "project=" + project +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
