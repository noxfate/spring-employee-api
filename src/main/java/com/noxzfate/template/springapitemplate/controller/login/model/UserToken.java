package com.noxzfate.template.springapitemplate.controller.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {
    @JsonProperty
    private String user;
    @JsonProperty("access_token")
    private String accessToken;

}
