package com.company;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Employee {
    private long id;
    private HashSet<EmployeeProject> projects;

    public Employee(long id) {
        this.id = id;
        this.projects = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HashSet<EmployeeProject> getProjects() {
        return projects;
    }

    public void setProjects(HashSet<EmployeeProject> projects) {
        this.projects = projects;
    }

    public void addProject(Project project, LocalDate startDate, LocalDate endDate){
        EmployeeProject employeeProject = new EmployeeProject(this,project,startDate,endDate);
        project.addEmployee(this);
        projects.add(employeeProject);
    }

    public void removeProject(Project project){
        projects.remove(project);
    }



    public static long getOverlappingDays(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {


        LocalDate startOverlap = start1.isBefore(start2) ? start2 : start1;
        LocalDate endOverlap = end1.isBefore(end2) ? end1 : end2;

        if (startOverlap.isAfter(endOverlap)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(startOverlap, endOverlap) + 1;
    }

    public long daysTogetherOnProjects(Employee employee, List<CommonProjects> commonProjects){
        long daysTogether=0;
        LocalDate startTogether= LocalDate.now();
        LocalDate endTogether= LocalDate.now();
        long overlapDays = 0;
        for (EmployeeProject project : this.getProjects()) {
            for (EmployeeProject project1 : employee.getProjects()) {
                if (project.getProject().equals(project1.getProject())) {
                    startTogether = project.getStartDate().isAfter(project1.getStartDate()) ? project.getStartDate() : project1.getStartDate();
                    endTogether = project.getEndDate().isBefore(project1.getEndDate()) ? project.getEndDate() : project1.getEndDate();
                    if(startTogether.isBefore(endTogether)){
                    for(CommonProjects commonProjects1: commonProjects){
                        overlapDays += Employee.getOverlappingDays(commonProjects1.getStartDate(),commonProjects1.getEndDate(),startTogether,endTogether);
                    }
//                    System.out.println(this + "  " + employee);
//                    System.out.println(startTogether + "  " + endTogether);
//                    System.out.println(overlapDays);
//                    System.out.println(daysTogether);
//                    System.out.println(startTogether.until(endTogether, ChronoUnit.DAYS)+1);
                    daysTogether = (daysTogether + startTogether.until(endTogether, ChronoUnit.DAYS)+1) - overlapDays;
//                    System.out.println(daysTogether);
                    commonProjects.add(new CommonProjects(this,employee,startTogether,endTogether,startTogether.until(endTogether, ChronoUnit.DAYS)+1));
                }}

            }
        }

 return daysTogether;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", projects=" + projects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
