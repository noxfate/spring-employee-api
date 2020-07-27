package com.noxzfate.template.springapitemplate.controller.employee.service;

import com.noxzfate.template.springapitemplate.controller.employee.api.EmployeeApiDelegate;
import com.noxzfate.template.springapitemplate.controller.employee.mapper.EmployeeMapper;
import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.controller.employee.validator.EmployeeValidator;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import com.noxzfate.template.springapitemplate.persistence.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService implements EmployeeApiDelegate {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployee() {
        var allEmployee = employeeRepository.findAll();
        List<Employee> result = allEmployee
                .stream()
                .map(item -> employeeMapper.toEmployeeModel(item))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeId(BigDecimal id) {
        try {
            employeeRepository.deleteById(id.longValue());
        } catch(EmptyResultDataAccessException nfe){
            log.info("No data found when try to delete employee ID: {}", id);
        } finally {
            return ResponseEntity.noContent().build();
        }

    }

    @Override
    public ResponseEntity<Employee> getEmployeeId(BigDecimal id) {
        var employeeResult = employeeRepository.findById(id.longValue());

        if (employeeResult.isPresent()) {
            var result = employeeMapper.toEmployeeModel(employeeResult.get());
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Employee> postEmployee(Employee employee) {
        EmployeeValidator.validateEmployeeRequest(employee);

        EmployeeEntity entity = employeeMapper.toEmployeeEntity(employee);
        entity = employeeRepository.saveAndFlush(entity);

        var response = employeeMapper.toEmployeeModel(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Employee> putEmployeeId(BigDecimal id, Employee employee) {

        EmployeeValidator.validateMatchId(id, employee);
        EmployeeValidator.validateEmployeeRequest(employee);

        EmployeeEntity entity = employeeMapper.toEmployeeEntity(employee);
        entity = employeeRepository.saveAndFlush(entity);

        var response = employeeMapper.toEmployeeModel(entity);
        return ResponseEntity.ok(response);
    }
}
