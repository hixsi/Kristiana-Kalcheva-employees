package com.company;

import java.util.HashSet;
import java.util.Objects;

public class Project {
    private long id;
    private HashSet<Employee> employees;

    public Project(long id) {
        this.id = id;
        this.employees = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployees(HashSet<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeProject(Employee employee){
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
