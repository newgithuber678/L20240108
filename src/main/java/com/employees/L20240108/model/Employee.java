package com.employees.L20240108.model;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private int salary;
    private int department;


    public Employee(String firstName, String lastName, int salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,salary,department);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o)return true;
        if(o==null || getClass()!=o.getClass())return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName,employee.firstName)&&Objects.equals(lastName,employee.lastName)&&(salary==employee.salary)&&(department==employee.department);
    }
}
