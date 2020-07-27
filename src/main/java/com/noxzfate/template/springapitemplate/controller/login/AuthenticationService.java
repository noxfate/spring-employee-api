package com.noxzfate.template.springapitemplate.controller.login;

import com.noxzfate.template.springapitemplate.constants.SecurityConstant;
import com.noxzfate.template.springapitemplate.controller.login.model.UserCredentials;
import com.noxzfate.template.springapitemplate.controller.login.model.UserToken;
import com.noxzfate.template.springapitemplate.exception.BusinessException;
import com.noxzfate.template.springapitemplate.persistence.model.AccountEntity;
import com.noxzfate.template.springapitemplate.persistence.repository.AccountRepository;
import com.noxzfate.template.springapitemplate.utils.PasswordUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private AccountRepository accountRepository;

    public AuthenticationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private String getJWTToken(String username, Map<String, Object> userClaims) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_ADMIN");

        String token = Jwts.builder()
                .setId("employee-app")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .addClaims(userClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512,
                        SecurityConstant.SECRET)
                .compact();

        return token;
    }

    public UserToken authenticate(UserCredentials credential) {
        Optional<AccountEntity> userAccount = accountRepository.findByUsernameAndPassword(credential.getUsername(),
                PasswordUtils.hashPassword(credential.getPassword()));
        if (userAccount.isPresent()) {
            // get user roles and additional attribute to put in your JWT claims
            String token = getJWTToken(credential.getUsername(), Collections.EMPTY_MAP);
            return UserToken.builder().user(credential.getUsername()).accessToken(token).build();
        } else {
            throw BusinessException.builder()
                    .errorCode("APIx03")
                    .message("Invalid credential")
                    .description("Please input correct username and password")
                    .build();
        }
    }
}
