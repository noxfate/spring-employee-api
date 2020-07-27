package com.noxzfate.template.springapitemplate.controller.employee.service;

import com.noxzfate.template.springapitemplate.controller.employee.mapper.EmployeeMapper;
import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import com.noxzfate.template.springapitemplate.persistence.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Spy
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService service; // test subject

    private EmployeeEntity mockEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(Long.valueOf(1));
        employeeEntity.setType("normal");
        employeeEntity.setFistName("Foo");
        employeeEntity.setLastName("Bar");
        return employeeEntity;
    }

    @Test
    public void testGetEmployeeList_givenEmployeesList_shouldSuccess() {
        EmployeeEntity emp1 = mockEntity();
        EmployeeEntity emp2 = mockEntity();

        Mockito.lenient().when(employeeRepository.findAll())
                .thenReturn(List.of(emp1, emp2));

        ResponseEntity<List<Employee>> response = service.getEmployee();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 2);
    }

    @Test
    public void testGetEmployeeList_givenEmptyResult_shouldSuccess() {
        Mockito.lenient().when(employeeRepository.findAll())
                .thenReturn(Collections.EMPTY_LIST);

        ResponseEntity<List<Employee>> response = service.getEmployee();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().size(), 0);
    }

    @Test
    public void testGetEmployeeById_givenEmployee_shouldSuccess() {
        EmployeeEntity emp1 = mockEntity();

        Mockito.lenient().when(employeeRepository.findById(any()))
                .thenReturn(Optional.of(emp1));

        ResponseEntity<Employee> response = service.getEmployeeId(BigDecimal.ONE);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());

        assertEquals(response.getBody().getId().intValue(), emp1.getId().intValue());
        assertEquals(response.getBody().getType().getValue(), emp1.getType());
    }

    @Test
    public void testGetEmployeeById_givenNoResult_shouldReturnNotfound() {
        Mockito.lenient().when(employeeRepository.findById(any()))
                .thenReturn(Optional.empty());

        ResponseEntity<Employee> response = service.getEmployeeId(BigDecimal.ONE);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    // add more test


}
