package com.noxzfate.template.springapitemplate.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessException extends RuntimeException {
    private String errorCode;
    private String message;
    private String description;

}
