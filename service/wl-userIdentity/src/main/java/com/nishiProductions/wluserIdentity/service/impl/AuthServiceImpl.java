package com.nishiProductions.wluserIdentity.service.impl;

import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.service.AuthService;
import com.nishiProductions.wluserIdentity.service.business.AuthBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthBusinessService authBusinessService;

    @Override
    public UserDto authenticate(UserDto userDto) {
        log.info("-------------AuthServiceImpl--------------",userDto);
        return null;
    }
}
