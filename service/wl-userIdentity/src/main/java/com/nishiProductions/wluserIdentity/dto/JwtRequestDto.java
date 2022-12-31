package com.nishiProductions.wluserIdentity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequestDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String userName;
    private String email;
    private String password;
    private String grantType;
    private String refreshToken;
    private String agentRegNumber;
}
