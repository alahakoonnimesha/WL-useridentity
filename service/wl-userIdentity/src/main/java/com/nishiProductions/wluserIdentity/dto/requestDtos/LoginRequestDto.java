package com.nishiProductions.wluserIdentity.dto.requestDtos;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
