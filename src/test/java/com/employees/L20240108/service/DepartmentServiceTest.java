package com.employees.L20240108.service;

import com.employees.L20240108.exceptions.EmployeeNotFoundException;
import com.employees.L20240108.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        var employees = List.of(
                new Employee("test1", "testtest", 1000, 1),
                new Employee("test2", "test2", 10000, 1),
                new Employee("test3", "testt3est", 2000, 2),
                new Employee("test4", "testte4st", 20000, 2),
                new Employee("test5", "testte5st", 200000, 2),
                new Employee("test6", "testtes6t", 300000, 3)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void testDepartmentMaxSalary() {
        assertThat(departmentService.findMaxSalary(1)).isEqualTo(new Employee("test2", "test2", 10000, 1));
        assertThat(departmentService.findMaxSalary(2)).isEqualTo(new Employee("test5", "testte5st", 200000, 2));
        assertThat(departmentService.findMaxSalary(3)).isEqualTo(new Employee("test6", "testtes6t", 300000, 3));
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findMaxSalary(1111));
    }

    @Test
    void testDepartmentMinSalary() {
        var actual1 = departmentService.findMinSalary(1);
        assertThat(actual1.isPresent()).isTrue();
        assertThat(actual1.get()).isEqualTo(new Employee("test1", "testtest", 1000, 1));
        var actual2 = departmentService.findMinSalary(3);
        assertThat(actual2.isPresent()).isTrue();
        assertThat(actual2.get()).isEqualTo(new Employee("test6", "testtes6t", 300000, 3));
        var actual3 = departmentService.findMinSalary(1111);
        assertThat(actual3.isEmpty()).isTrue();
    }

    @Test
    void testFindByDepartment() {
        var actual = departmentService.findByDepartment(2);
        assertThat(actual).containsExactlyInAnyOrder(
                new Employee("test3", "testt3est", 2000, 2),
                new Employee("test4", "testte4st", 20000, 2),
                new Employee("test5", "testte5st", 200000, 2)
        );
        assertThat(departmentService.findByDepartment(111111)).isEmpty();
    }

    @Test
    void testGroupByDepartment() {
        var actual = departmentService.groupByDepartment();
        var expected= Map.of(
                1, List.of(new Employee("test1", "testtest", 1000, 1),
                        new Employee("test2", "test2", 10000, 1)
                ),
                2, List.of(new Employee("test3", "testt3est", 2000, 2),
                        new Employee("test4", "testte4st", 20000, 2),
                        new Employee("test5", "testte5st", 200000, 2)
                ),
                3, List.of(new Employee("test6", "testtes6t", 300000, 3))
        );
        assertThat(actual).isEqualTo(expected);
    }
}