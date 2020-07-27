package com.noxzfate.template.springapitemplate.controller.employee.mapper;

import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeMapper {
    public Employee toEmployeeModel(EmployeeEntity employeeInput) {
        Employee employee = new Employee();
        Optional.ofNullable(employeeInput.getId())
                .ifPresent(emp -> employee.setId(emp.intValue()));
        employee.setType(Employee.TypeEnum.fromValue(employeeInput.getType()));
        employee.setFirstName(employeeInput.getFistName());
        employee.setLastName(employeeInput.getLastName());
        return employee;
    }

    public EmployeeEntity toEmployeeEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        Optional.ofNullable(employee.getId())
                .ifPresent(id -> employeeEntity.setId(id.longValue()));
        employeeEntity.setType(String.valueOf(employee.getType()));
        employeeEntity.setFistName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        return employeeEntity;
    }
}
