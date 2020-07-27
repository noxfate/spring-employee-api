package com.noxzfate.template.springapitemplate.utils;

import com.noxzfate.template.springapitemplate.persistence.model.AccountEntity;
import com.noxzfate.template.springapitemplate.persistence.model.EmployeeEntity;
import com.noxzfate.template.springapitemplate.persistence.repository.AccountRepository;
import com.noxzfate.template.springapitemplate.persistence.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppSimpleInitialize {

    private EmployeeRepository employeeRepository;
    private AccountRepository accountRepository;

    public AppSimpleInitialize(EmployeeRepository employeeRepository, AccountRepository accountRepository) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Spring context has been initialized");
        EmployeeEntity johnDoe = initJohnDoe();
        log.info("Successfully init employee: ID [{}]", johnDoe.getId());
        initAdminUser();
        log.info("Successfully init user admin: Password {}", "admin");
    }

    private EmployeeEntity initJohnDoe() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setType("normal");
        employeeEntity.setFistName("John");
        employeeEntity.setLastName("Doe");
        EmployeeEntity result = employeeRepository.saveAndFlush(employeeEntity);
        return result;
    }


    private AccountEntity initAdminUser() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername("admin");
        accountEntity.setPassword(PasswordUtils.hashPassword("admin"));
        AccountEntity result = accountRepository.saveAndFlush(accountEntity);
        return result;
    }
}
