package com.nishiProductions.wluserIdentity.service;

import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    ResponseDto saveUser(@RequestBody UserDto userDto);

    ResponseDto loginWithoutAuth(LoginRequestDto loginRequestDto);

    ResponseDto login(LoginRequestDto loginRequestDto);

    ResponseDto getAllUsers();

    ResponseDto getUserByEmail(String email);
}
