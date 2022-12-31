package com.nishiProductions.wluserIdentity.controller;

import com.nishiProductions.wluserIdentity.dto.JwtRequestDto;
import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.service.JwtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    JwtUserService jwtUserService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseDto authenticate(@RequestBody JwtRequestDto jwtRequestDto) {
        log.info("-------------AuthController--------------", jwtRequestDto);
        return jwtUserService.authenticate(jwtRequestDto);
    }
}
