package com.company;

public class CommonProjects implements Comparable<CommonProjects> {
    private Employee employee1;
    private Employee employee2;
//    private Project project;
    private long duration;

    public CommonProjects(Employee employee1, Employee employee2, long duration) {
        this.employee1 = employee1;
        this.employee2 = employee2;
//        this.project = project;
        this.duration = duration;
    }


    public Employee getEmployee1() {
        return employee1;
    }

    public void setEmployee1(Employee employee1) {
        this.employee1 = employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public void setEmployee2(Employee employee2) {
        this.employee2 = employee2;
    }

//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public int compareTo(CommonProjects commonProject) {
        return Long.compare(this.duration,commonProject.duration);
    }

    @Override
    public String toString() {
        return "CommonProjects{" +
                "employee1=" + employee1 +
                ", employee2=" + employee2 +
//                ", project=" + project +
                ", duration=" + duration +
                '}';
    }
}

