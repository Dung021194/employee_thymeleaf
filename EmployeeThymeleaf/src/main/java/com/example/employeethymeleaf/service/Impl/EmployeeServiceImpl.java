package com.example.employeethymeleaf.service.Impl;



import com.example.employeethymeleaf.model.Address;
import com.example.employeethymeleaf.model.Employee;
import com.example.employeethymeleaf.service.ICrudService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements ICrudService<Employee> {
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();
        employees.add(new Employee("Ngọc", 20, 10000d, new Address(1L,"Ha noi")));
    }

    @Override
    public List<Employee> findAll() {
        return employees;
    }

    @Override
    public Employee findById(Long id) {
        for (Employee e : employees) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            if (employees.isEmpty()){
                employee.setId(1L);
            }else {
                employee.setId(employees.get(employees.size() - 1).getId() + 1);
            }
            employees.add(employee);
        } else {
            Employee employeeUpdate = findById(employee.getId());
            if (employeeUpdate != null) {
                employeeUpdate.setName(employee.getName());
                employeeUpdate.setAge(employee.getAge());
                employeeUpdate.setSalary(employee.getSalary());
                employeeUpdate.setAddress(employee.getAddress());
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        Employee employee = findById(id);
        if (employee != null) {
            employees.remove(employee);
        }
    }
}