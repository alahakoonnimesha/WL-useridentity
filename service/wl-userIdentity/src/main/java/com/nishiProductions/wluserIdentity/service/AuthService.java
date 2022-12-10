package com.nishiProductions.wluserIdentity.service;

import com.nishiProductions.wluserIdentity.dto.UserDto;

public interface AuthService {

    UserDto authenticate(UserDto userDto);
}
