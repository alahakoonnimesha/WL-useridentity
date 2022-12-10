package com.nishiProductions.wluserIdentity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Long userId;
    private String userRegNo;
    private String userName;
    private String contactNo;
    private String email;
    private String password;
    private Boolean isActive;

}
