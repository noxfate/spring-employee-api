package com.noxzfate.template.springapitemplate.controller.login.controller;

import com.noxzfate.template.springapitemplate.controller.login.AuthenticationService;
import com.noxzfate.template.springapitemplate.controller.login.model.UserCredentials;
import com.noxzfate.template.springapitemplate.controller.login.model.UserToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class LoginController {

    private AuthenticationService authenticateService;

    public LoginController(AuthenticationService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @RequestMapping(value = "/auth", method= RequestMethod.POST)
    public ResponseEntity<UserToken> login(@RequestBody @Valid UserCredentials credential) {
        UserToken token = authenticateService.authenticate(credential);
        return ResponseEntity.ok(token);
    }

}
