package com.noxzfate.template.springapitemplate.controller.employee.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-07-27T00:58:47.636171+07:00[Asia/Bangkok]")
@Controller
@RequestMapping("${openapi.employee.base-path:}")
public class EmployeeApiController implements EmployeeApi {

    private final EmployeeApiDelegate delegate;

    public EmployeeApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) EmployeeApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new EmployeeApiDelegate() {});
    }

    @Override
    public EmployeeApiDelegate getDelegate() {
        return delegate;
    }

}
