package com.noxzfate.template.springapitemplate.controller.employee.validator;

import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import com.noxzfate.template.springapitemplate.exception.BusinessException;

import java.math.BigDecimal;

public class EmployeeValidator {

    public static void validateEmployeeRequest(Employee employee) {
        // Business validation on request
    }

    public static void validateMatchId(BigDecimal id, Employee employee) {
        if (id == null || employee == null || !id.equals(BigDecimal.valueOf(employee.getId()))){
            throw BusinessException
                    .builder()
                    .errorCode("APIx02")
                    .message("Resource ID mismatch")
                    .description("Please align your ID within request")
                    .build();
        }
    }
}
