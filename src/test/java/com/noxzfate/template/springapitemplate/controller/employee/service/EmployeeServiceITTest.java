package com.noxzfate.template.springapitemplate.controller.employee.service;

import com.noxzfate.template.springapitemplate.controller.login.AuthenticationService;
import com.noxzfate.template.springapitemplate.controller.login.model.UserCredentials;
import com.noxzfate.template.springapitemplate.controller.login.model.UserToken;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import com.noxzfate.template.springapitemplate.persistence.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeServiceITTest {

    private String token;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Before
    public void getToken() {
        UserCredentials login = new UserCredentials();
        login.setUsername("admin");
        login.setPassword("admin");
        UserToken result = authenticationService.authenticate(login);
        token = "Bearer " + result.getAccessToken();
    }


    @Test
    public void testGetAllEmployee_givenToken_shouldSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", token)
                )
                .andExpect(status().isOk())
        ;

    }

    @Test
    public void testGetAllEmployee_givenNoToken_shouldForbidden() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee")
                .contentType("application/json")
                .accept("application/json")
        )
                .andExpect(status().isForbidden())
        ;

    }

    @Test
    public void testGetEmployeeId_givenToken_shouldSuccess() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/1")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", token)
        )
                .andExpect(status().isOk())
        ;

    }

    @Test
    public void testGetEmployeeId_givenNoToken_shouldForbidden() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/1")
                .contentType("application/json")
                .accept("application/json")
        )
                .andExpect(status().isForbidden())
        ;

    }

    @Test
    public void testGetEmployeeId_givenInvalidEmployee_shouldNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/2")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", token)
        )
                .andExpect(status().isNotFound())
        ;
    }


    @Test
    public void testDeleteEmployeeId_givenNoToken_shouldForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/1")
                .contentType("application/json")
                .accept("application/json")
        )
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    public void testDeleteEmployeeId_givenExistEmployee_shouldNoContent() throws Exception {
        EmployeeEntity mock = new EmployeeEntity();
        mock.setId(Long.valueOf(99));
        mock.setType("normal");
        employeeRepository.saveAndFlush(mock);

        List<EmployeeEntity> before = employeeRepository.findAll();
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/99")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", token)
        )
                .andExpect(status().isNoContent())
        ;

        assertEquals(2, before.size());
    }

    @Test
    public void testDeleteEmployeeId_givenNotExistEmployee_shouldNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/200")
                .contentType("application/json")
                .accept("application/json")
                .header("Authorization", token)
        )
                .andExpect(status().isNoContent())
        ;
    }

    // add more integration test

}
