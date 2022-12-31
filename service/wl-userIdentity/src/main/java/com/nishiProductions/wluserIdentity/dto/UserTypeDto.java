package com.nishiProductions.wluserIdentity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTypeDto implements Serializable {

    private static final long serialVersionUID = -5552254524708038284L;
    private Long userTypeId;
    private String userTypeDesc;

}
