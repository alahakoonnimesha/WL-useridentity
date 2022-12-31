package com.nishiProductions.wluserIdentity.dto.responseDtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponseDto implements Serializable {

    private String token;
    private String refreshToken;

}
