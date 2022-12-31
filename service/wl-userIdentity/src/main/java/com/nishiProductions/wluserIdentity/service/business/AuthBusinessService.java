package com.nishiProductions.wluserIdentity.service.business;

import com.nishiProductions.wluserIdentity.config.JwtTokenUtil;
import com.nishiProductions.wluserIdentity.dto.JwtRequestDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.responseDtos.JwtResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthBusinessService {


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserBusinessService userBusinessService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public JwtResponseDto authenticate(JwtRequestDto jwtRequestDto, UserDto userDto) {
        log.info("-------------AuthServiceImpl--------------", jwtRequestDto);
        JwtResponseDto jwtResponseDto = null;
        if (userDto != null && jwtRequestDto.getGrantType() != null
                && jwtRequestDto.getGrantType().equalsIgnoreCase(userDto.getUserTypeDto().getUserTypeDesc())) {

            final String token = jwtTokenUtil.generateToken(userDto.getUserName(), userDto);
            final String refreshToken = jwtTokenUtil.generateRefreshToken(userDto, userDto);
            jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setToken(token);
            jwtResponseDto.setRefreshToken(refreshToken);
        }
        return jwtResponseDto;
    }
}
