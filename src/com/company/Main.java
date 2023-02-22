package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static long maxDaysTogetherEmployees(List<Employee> employeeList, List<Employee> employeesTogetherMax){
        long maxDays = 0;

        for (int i = 0; i < employeeList.size() - 1; i++) {
            for (int j = i + 1; j < employeeList.size(); j++) {

                Employee employee = employeeList.get(i);
                Employee employee1 = employeeList.get(j);
//                List<CommonProjects> commonProjects = employee.commonProjects(employee1);
//                commonProjects.stream().forEach(System.out::println);
//                long daysTogether = employee.daysTogetherOnProjects(employee1,commonProjects);
                long daysTogether = employee.commonProjects(employee1).getDuration();


                if(maxDays < daysTogether){
                    maxDays = daysTogether;
                    employeesTogetherMax.clear();
                    employeesTogetherMax.add(employee);
                    employeesTogetherMax.add(employee1);
                }
            }
        }
        return maxDays;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        if (fileName.endsWith(".txt")) {

            List<Employee> employeeList = new ArrayList<>();
            List<Project> projectList = new ArrayList<>();

            List<Employee> employeesTogetherMax = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;

                // reading from cvs file
                while ((line = reader.readLine()) != null) {

                    String[] splitData = line.split(",");

                       long employeeId ;
                       long projectId;
                       LocalDate startDate = LocalDate.now();
                       LocalDate endDate= LocalDate.now();

                        // parsing data

                        employeeId = Long.parseLong(splitData[0].trim());

                        projectId = Long.parseLong(splitData[1].trim());
                           if (!splitData[2].trim().equals("NULL")){
                        startDate = LocalDate.parse(splitData[2].trim());}
                           if (!splitData[3].trim().equals("NULL")){
                        endDate = LocalDate.parse(splitData[3].trim());}

                        // creating objects

                        Employee employee = new Employee(employeeId);
                        Project project = new Project(projectId);

                    if(!employeeList.contains(employee)){
                        employeeList.add(employee);
                    }
                    else{
                        employee = employeeList.get(employeeList.indexOf(employee));
                    }

                    Project existingProject = null;
                    for (Project p : projectList) {
                        if (p.getId() == project.getId()) {
                            existingProject = p;
                            break;
                        }
                    }

                    if (existingProject == null) {
                        projectList.add(project);
                        employee.addProject(project, startDate, endDate);
                    } else {
                        employee.addProject(existingProject, startDate, endDate);
                    }



                }
                sc.close();


                long maxDays = maxDaysTogetherEmployees(employeeList,employeesTogetherMax);

            }

            catch(IOException e){
                e.printStackTrace();
            }

            System.out.println(employeesTogetherMax.stream()
                    .map(Employee::getId)
                    .map(Object::toString)
                    .collect(Collectors.joining(", ")));

        }
        else{
            System.out.println("It must be CVS file.");
        }

    }
}
