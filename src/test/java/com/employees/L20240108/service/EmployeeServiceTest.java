package com.employees.L20240108.service;

import com.employees.L20240108.exceptions.EmployeeAlreadyAddedException;
import com.employees.L20240108.exceptions.EmployeeNotFoundException;
import com.employees.L20240108.exceptions.EmployeeStorageIsFullException;
import com.employees.L20240108.exceptions.WrongNameException;
import com.employees.L20240108.model.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;


class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void testAdd() {
        employeeService.add("test", "testtest", 1000, 1);
        employeeService.add("tEsTTT", "tesTTeeest", 2000, 3);

        var actual1 = employeeService.find("test", "TESTTEST");
        assertThat(actual1).isNotNull();
        assertThat(actual1.getFirstName()).isEqualTo("test");
        assertThat(actual1.getLastName()).isEqualTo("testtest");
        assertThat(actual1.getDepartment()).isEqualTo(1);
        assertThat(actual1.getSalary()).isEqualTo(1000);

        var actual2 = employeeService.find("TESTTT", "TEsTTeEeSt");
        assertThat(actual2).isNotNull();
        assertThat(actual2.getFirstName()).isEqualTo("tEsTTT");
        assertThat(actual2.getLastName()).isEqualTo("tesTTeeest");
        assertThat(actual2.getDepartment()).isEqualTo(3);
        assertThat(actual2.getSalary()).isEqualTo(2000);
    }

    @Test
    void testAddDuplicate() {
        employeeService.add("test", "testtest", 1000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("test", "testtest", 2000, 2));
    }

    @Test
    void testFull() {
        employeeService.add("testt", "testtest", 1000, 1);
        employeeService.add("testtt", "testtest", 1000, 1);
        employeeService.add("testttt", "testtest", 1000, 1);
        employeeService.add("testtttt", "testtest", 1000, 1);
        employeeService.add("testttttt", "testtest", 1000, 1);
        employeeService.add("testtttttt", "testtest", 1000, 1);
        employeeService.add("testttttttt", "testtest", 1000, 1);
        employeeService.add("testtttttttt", "testtest", 1000, 1);
        employeeService.add("testttttttttt", "testtest", 1000, 1);
        employeeService.add("testtttttttttt", "testtest", 1000, 1);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add("testtesttest", "testtest", 1000, 1));
    }

    @Test
    void testWrongName() {
        assertThrows(WrongNameException.class, () -> employeeService.add("te1st", "testtest", 1000, 1));
        assertThrows(WrongNameException.class, () -> employeeService.add("te1st", "tes1ttest", 1000, 1));
    }

    @Test
    void testGetAll() {
        employeeService.add("testt", "testtest", 1000, 1);
        employeeService.add("testtt", "testtest", 2000, 2);
        var actual = employeeService.getAll();
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("testt", "testtest", 1000, 1),
                new Employee("testtt", "testtest", 2000, 2)
        );
    }

    @Test
    void testNotFound(){
        assertThrows(EmployeeNotFoundException.class,()->employeeService.find("tttt","ttttttt"));
    }
    @Test
    void testRemove(){
        assertThrows(EmployeeNotFoundException.class,()->employeeService.remove("tttt","ttttttt"));
        employeeService.add("testt", "testtest", 1000, 1);
        employeeService.add("testtt", "testtest", 2000, 2);
        var actual=employeeService.find("testt", "testtest");
        assertThat(actual).isNotNull();
        employeeService.remove("testt", "testtest");
        assertThrows(EmployeeNotFoundException.class,()->employeeService.find("testt", "testtest"));
    }
}