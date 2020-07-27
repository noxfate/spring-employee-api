package com.noxzfate.template.springapitemplate.controller.employee.mapper;

import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeMapperTest {

    private EmployeeMapper mapper = new EmployeeMapper();

    @Test
    public void testToEmployeeModel_givenCorrect_shouldMapCorrect() {
        EmployeeEntity mockEntity = mockEntity();
        Employee employee = mapper.toEmployeeModel(mockEntity);
        assertEquals(employee.getId().intValue(), mockEntity.getId().intValue());
        assertEquals(employee.getType().getValue(), mockEntity.getType());
        assertEquals(employee.getFirstName(), mockEntity.getFistName());
        assertEquals(employee.getLastName(), mockEntity.getLastName());
    }

    @Test
    public void testToEmployeeModel_givenNullId_shouldMapCorrect() {
        EmployeeEntity mockEntity = mockEntity();
        mockEntity.setId(null);
        Employee employee = mapper.toEmployeeModel(mockEntity);
        assertEquals(employee.getId(), mockEntity.getId());
        assertEquals(employee.getType().getValue(), mockEntity.getType());
        assertEquals(employee.getFirstName(), mockEntity.getFistName());
        assertEquals(employee.getLastName(), mockEntity.getLastName());
    }

    private EmployeeEntity mockEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(Long.valueOf(1));
        employeeEntity.setType("normal");
        employeeEntity.setFistName("Foo");
        employeeEntity.setLastName("Bar");
        return employeeEntity;
    }

    @Test
    public void testToEmployeeEntity_givenCorrect_shouldMapCorrect() {
        Employee mockReq = mockEmployeeReq();
        EmployeeEntity entity = mapper.toEmployeeEntity(mockReq);
        assertEquals(entity.getId().intValue(), mockReq.getId().intValue());
        assertEquals(entity.getType(), mockReq.getType().getValue());
        assertEquals(entity.getFistName(), mockReq.getFirstName());
        assertEquals(entity.getLastName(), mockReq.getLastName());
    }

    @Test
    public void testToEmployeeEntity_givenNullId_shouldMapCorrect() {
        Employee mockReq = mockEmployeeReq();
        mockReq.setId(null);
        EmployeeEntity entity = mapper.toEmployeeEntity(mockReq);
        assertEquals(entity.getId(), mockReq.getId());
        assertEquals(entity.getType(), mockReq.getType().getValue());
        assertEquals(entity.getFistName(), mockReq.getFirstName());
        assertEquals(entity.getLastName(), mockReq.getLastName());
    }

    private Employee mockEmployeeReq() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setType(Employee.TypeEnum.NORMAL);
        employee.setFirstName("Foo");
        employee.setLastName("Bar");
        return employee;
    }

}
