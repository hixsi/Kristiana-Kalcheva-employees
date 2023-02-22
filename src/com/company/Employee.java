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

    public CommonProjects commonProjects(Employee employee){
        long daysTogether=0;

        List<CommonProjects> commonProjects = new ArrayList<>();
        LocalDate maxStartDate = LocalDate.MIN;
        LocalDate minEndDate = LocalDate.MAX;
        for (EmployeeProject project : this.getProjects()) {
            for (EmployeeProject project1 : employee.getProjects()) {
                if (project.getProject().equals(project1.getProject())) {
                    LocalDate startTogether = project.getStartDate().isAfter(project1.getStartDate()) ? project.getStartDate() : project1.getStartDate();
                    LocalDate endTogether = project.getEndDate().isBefore(project1.getEndDate()) ? project.getEndDate() : project1.getEndDate();
                    if (startTogether.isBefore(endTogether)) {
                        maxStartDate = maxStartDate.isBefore(startTogether) ? startTogether : maxStartDate;
                        minEndDate = minEndDate.isAfter(endTogether) ? endTogether : minEndDate;
                    }
                }

            }
        }
        daysTogether = maxStartDate.until(minEndDate, ChronoUnit.DAYS);
//        commonProjects.add(new CommonProjects(this,employee,daysTogether));


    return new CommonProjects(this,employee,daysTogether);
    }

    public long daysTogetherOnProjects(Employee employee, List<CommonProjects> commonProjects){


       long totalDaysTogether = 0;
       for(CommonProjects c: commonProjects)
           totalDaysTogether += c.getDuration();
       return totalDaysTogether;
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
