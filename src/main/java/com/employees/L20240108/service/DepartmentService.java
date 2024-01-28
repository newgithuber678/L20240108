package com.employees.L20240108.service;

import com.employees.L20240108.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findMaxSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public Employee findMinSalary(int department) {
        return employeeService.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
    }
    public Collection<Employee> findByDepartment(int department){
        return employeeService.getAll().stream()
                .filter(e->e.getDepartment()==department)
                .collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> groupByDepartment(){
        /*Map<Integer, List<Employee>> map=new HashMap<>();
        for (Employee employee:employeeService.getAll()){
            List<Employee> employeeByDept=map.get(employee.getDepartment());
            if (employeeByDept!=null){
                employeeByDept.add(employee);
            }
            else {
                List<Employee> emp=new ArrayList<>();
                emp.add(employee);
                map.put(employee.getDepartment(),emp);
            }
        }*/

        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
