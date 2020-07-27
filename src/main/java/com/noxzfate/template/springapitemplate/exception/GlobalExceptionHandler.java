package com.noxzfate.template.springapitemplate.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiErrorModel> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        log.error("Error are hanlding in global level : {}", e.getMessage(), e);
        ApiErrorModel apiErrorModel = new ApiErrorModel();
        apiErrorModel.setCode("APIx01");
        apiErrorModel.setCause("Unknown error has occurred");
        apiErrorModel.setDetail(e.getMessage());
        apiErrorModel.setTimestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorModel);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ApiErrorModel> businessExceptionHandler(HttpServletRequest req, BusinessException e) throws Exception {
        log.error("Error are hanlding in global level : {}", e.getMessage(), e);
        ApiErrorModel apiErrorModel = new ApiErrorModel();
        apiErrorModel.setCode(e.getErrorCode());
        apiErrorModel.setCause(e.getMessage());
        apiErrorModel.setDetail(e.getDescription());
        apiErrorModel.setTimestamp(OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiErrorModel);
    }
}
