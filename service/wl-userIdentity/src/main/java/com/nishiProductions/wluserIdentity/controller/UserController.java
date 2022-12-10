package com.nishiProductions.wluserIdentity.controller;

import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import com.nishiProductions.wluserIdentity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseDto saveUser(@RequestBody UserDto userDto) {
        log.info("UserController.saveUser() invoked.");
        return userService.saveUser(userDto);
    }

    @GetMapping("/allUsers")
    public ResponseDto getAllUsers() {
        log.info("UserController.saveUser() invoked.");
        return userService.getAllUsers();
    }

    @PostMapping("/loginWithoutAuth")
    public ResponseDto loginWithoutAuth(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("UserController.login() invoked.");
        return userService.loginWithoutAuth(loginRequestDto);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("UserController.login() invoked.");
        return userService.login(loginRequestDto);
    }
}
