package com.employees.L20240108.service;

import com.employees.L20240108.exceptions.EmployeeAlreadyAddedException;
import com.employees.L20240108.exceptions.EmployeeNotFoundException;
import com.employees.L20240108.exceptions.EmployeeStorageIsFullException;
import com.employees.L20240108.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private static final int MAX_COUNT = 10;
    private final List<Employee> employees = new ArrayList<>(MAX_COUNT);

    public void add(String firstName, String lastName) {
        if (employees.size() >= MAX_COUNT)
            throw new EmployeeStorageIsFullException();
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) throw new EmployeeAlreadyAddedException();
        employees.add(employee);
    }

    public void remove(String firstName, String lastName) {
        var it=employees.iterator();
        while(it.hasNext()) {
            var employee=it.next();
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){ it.remove(); return;}
        }
        throw new EmployeeNotFoundException();
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) return employee;
        }
        throw new EmployeeNotFoundException();

    }
    public Collection<Employee> getAll(){
        return Collections.unmodifiableList(employees);
    }
}
