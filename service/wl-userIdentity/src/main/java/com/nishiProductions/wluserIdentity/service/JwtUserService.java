package com.nishiProductions.wluserIdentity.service;

import com.nishiProductions.wluserIdentity.dto.JwtRequestDto;
import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUserService {

    UserDetails loadUserByEmail(String email);

    ResponseDto authenticate(JwtRequestDto jwtRequestDto);

}
