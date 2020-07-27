package com.noxzfate.template.springapitemplate.exception;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ApiErrorModel {
    private String code;
    private String cause;
    private String detail;
    private OffsetDateTime timestamp;
}
