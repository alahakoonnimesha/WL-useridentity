package com.nishiProductions.wluserIdentity.service.impl;

import com.nishiProductions.wluserIdentity.constants.ApplicationMessageConstants;
import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.requestDtos.LoginRequestDto;
import com.nishiProductions.wluserIdentity.service.UserService;
import com.nishiProductions.wluserIdentity.service.business.UserBusinessService;
import com.nishiProductions.wluserIdentity.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserBusinessService userBusinessService;

    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public ResponseDto saveUser(UserDto userDto) {
        log.info("UserServiceImpl.saveUser() invoked.");
        ResponseDto responseDto = null;
        try {
            UserDto savedUserDto = userBusinessService.saveUser(userDto);
            if (savedUserDto != null) {
                log.info("UserDto Saved successfully---------{}", savedUserDto);
                responseDto = serviceUtil.getServiceResponse(savedUserDto);
            } else {
                log.info("Unable to save user");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_USER);
            }
        } catch (Exception e) {
            log.error("Exception occurs while saving user.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_USER);
        }
        return responseDto;
    }

    @Override
    public ResponseDto loginWithoutAuth(LoginRequestDto loginRequestDto) {
        log.info("UserServiceImpl.login() invoked.");
        ResponseDto responseDto = null;
        try {
            UserDto retrievedUserDto = userBusinessService.loginWithoutAuth(loginRequestDto);
            if (retrievedUserDto != null) {
                log.info("UserDto retrieved successfully---------{}", retrievedUserDto);
                responseDto = serviceUtil.getServiceResponse(retrievedUserDto);
            } else {
                log.info("Unable to retrieve all users.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USERS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving users.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USERS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto login(LoginRequestDto loginRequestDto) {
        log.info("UserServiceImpl.login() invoked.");
        ResponseDto responseDto = null;
        try {
            UserDto retrievedUserDto = userBusinessService.login(loginRequestDto);
            if (retrievedUserDto != null) {
                log.info("UserDto retrieved successfully---------{}", retrievedUserDto);
                responseDto = serviceUtil.getServiceResponse(retrievedUserDto);
            } else {
                log.info("Unable to retrieve all users.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USERS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving users.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USERS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllUsers() {
        log.info("UserServiceImpl.login() invoked.");
        ResponseDto responseDto = null;
        try {
            List<UserDto> retrievedUserDtoList = userBusinessService.getAllUsers();
            if (retrievedUserDtoList != null) {
                log.info("UserDto retrieved successfully---------{}", retrievedUserDtoList);
                responseDto = serviceUtil.getServiceResponse(retrievedUserDtoList);
            } else {
                log.info("Unable to retrieve all users.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USERS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving users.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USERS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getUserByEmail(String email) {
        log.info("UserServiceImpl.getUserByEmail() invoked.");
        ResponseDto responseDto = null;
        try {
            UserDto retrievedUserDto = userBusinessService.getUserByEmail(email);
            if (retrievedUserDto != null) {
                log.info("UserDto retrieved successfully---------{}", retrievedUserDto);
                responseDto = serviceUtil.getServiceResponse(retrievedUserDto);
            } else {
                log.info("Unable to retrieve all users.");
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USERS);
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving users.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USERS);
        }
        return responseDto;
    }
}
