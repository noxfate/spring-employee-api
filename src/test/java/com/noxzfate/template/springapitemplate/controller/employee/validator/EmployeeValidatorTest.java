package com.noxzfate.template.springapitemplate.controller.employee.validator;

import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.exception.BusinessException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeValidatorTest {

    private EmployeeValidator validator;

    @Test
    public void testValidateMatchID_givenMatchId_shouldPass() {
        Employee mockEmployeeReq = new Employee();
        mockEmployeeReq.setId(1);

        validator.validateMatchId(BigDecimal.ONE, mockEmployeeReq);
    }

    @Test(expected = BusinessException.class)
    public void testValidateMatchID_givenNotMatchId_shouldFail() {
        Employee mockEmployeeReq = new Employee();
        mockEmployeeReq.setId(2);
        validator.validateMatchId(BigDecimal.ONE, mockEmployeeReq);
    }

    @Test(expected = BusinessException.class)
    public void testValidateMatchID_givenIdNull_shouldFail() {
        Employee mockEmployeeReq = new Employee();
        mockEmployeeReq.setId(2);
        validator.validateMatchId(null, mockEmployeeReq);
    }

    @Test(expected = BusinessException.class)
    public void testValidateMatchID_givenEmployeeNull_shouldFail() {
        validator.validateMatchId(BigDecimal.ONE, null);
    }
}
