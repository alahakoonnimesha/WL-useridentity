package com.nishiProductions.wluserIdentity.service.business;

import com.nishiProductions.wluserIdentity.dao.UserDao;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserBusinessService {

    @Autowired
    UserDao userDao;

    public UserDto saveUser(UserDto userDto) {
        log.info("UserBusinessService.saveUser() invoked.");
        return userDao.saveUser(userDto);
    }

    public UserDto loginWithoutAuth(LoginRequestDto loginRequestDto) {
        log.info("UserBusinessService.login()");
        return userDao.getUserByUserNameAndPassword(loginRequestDto);
    }

    public UserDto login(LoginRequestDto loginRequestDto) {
        log.info("UserBusinessService.login()");
        return userDao.getUserByUserNameAndPassword(loginRequestDto);
    }

    public List<UserDto> getAllUsers() {
        log.info("UserBusinessService.login()");
        return userDao.getAllUsers();
    }

    public UserDto getUserByEmail(String email) {
        log.info("UserBusinessService.getUserByEmail()");
        return userDao.getUserByEmail(email);
    }
}
