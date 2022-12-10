package com.nishiProductions.wluserIdentity.dao;

import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;

import java.util.List;

public interface UserDao {

    UserDto saveUser(UserDto userDto);

    UserDto getUserByUserNameAndPassword(LoginRequestDto loginRequestDto);

    List<UserDto> getAllUsers();
}
