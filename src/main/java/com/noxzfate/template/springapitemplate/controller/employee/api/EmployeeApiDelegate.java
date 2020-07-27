package com.noxzfate.template.springapitemplate.controller.employee.api;

import java.math.BigDecimal;

import com.noxzfate.template.springapitemplate.controller.employee.model.Employee;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link EmployeeApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-27T00:58:47.636171+07:00[Asia/Bangkok]")
public interface EmployeeApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /employee/{id}
     * Delete employee by ID
     *
     * @param id Unique ID of employee (required)
     * @return No Content (status code 204)
     * @see EmployeeApi#deleteEmployeeId
     */
    default ResponseEntity<Void> deleteEmployeeId(BigDecimal id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /employee : Get Employee list
     * Get all list of employee available
     *
     * @return OK (status code 200)
     * @see EmployeeApi#getEmployee
     */
    default ResponseEntity<List<Employee>> getEmployee() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"id\" : 1, \"type\" : \"normal\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /employee/{id} : Your GET endpoint
     * Get one employee detail information by Id
     *
     * @param id Unique ID of employee (required)
     * @return OK (status code 200)
     *         or Not Found (status code 404)
     * @see EmployeeApi#getEmployeeId
     */
    default ResponseEntity<Employee> getEmployeeId(BigDecimal id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"id\" : 1, \"type\" : \"normal\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /employee : Create new employee
     * Create new employee
     *
     * @param employee Employee object (optional)
     * @return Created (status code 201)
     * @see EmployeeApi#postEmployee
     */
    default ResponseEntity<Employee> postEmployee(Employee employee) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"id\" : 1, \"type\" : \"normal\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    String exampleString = "<null> <id>1</id> <type>aeiou</type> <firstName>John</firstName> <lastName>Doe</lastName> </null>";
                    ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /employee/{id}
     * Update employee by ID
     *
     * @param id Unique ID of employee (required)
     * @param employee  (optional)
     * @return OK (status code 200)
     * @see EmployeeApi#putEmployeeId
     */
    default ResponseEntity<Employee> putEmployeeId(BigDecimal id,
        Employee employee) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Doe\", \"id\" : 1, \"type\" : \"normal\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
