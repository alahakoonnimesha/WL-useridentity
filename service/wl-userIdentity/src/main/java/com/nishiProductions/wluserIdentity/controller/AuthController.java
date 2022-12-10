package com.nishiProductions.wluserIdentity.controller;

import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.service.AuthService;
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
    AuthService authService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public UserDto authenticate(@RequestBody UserDto userDto){
        log.info("-------------AuthController--------------",userDto);
        return authService.authenticate(userDto);
    }
}
