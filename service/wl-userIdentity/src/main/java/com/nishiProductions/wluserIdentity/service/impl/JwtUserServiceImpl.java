package com.nishiProductions.wluserIdentity.service.impl;

import com.google.gson.Gson;
import com.nishiProductions.wluserIdentity.config.JwtTokenUtil;
import com.nishiProductions.wluserIdentity.constants.ApplicationMessageConstants;
import com.nishiProductions.wluserIdentity.dao.UserDao;
import com.nishiProductions.wluserIdentity.dto.JwtRequestDto;
import com.nishiProductions.wluserIdentity.dto.ResponseDto;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.responseDtos.JwtResponseDto;
import com.nishiProductions.wluserIdentity.modelMapper.UserModelMapper;
import com.nishiProductions.wluserIdentity.service.JwtUserService;
import com.nishiProductions.wluserIdentity.service.business.AuthBusinessService;
import com.nishiProductions.wluserIdentity.service.business.UserBusinessService;
import com.nishiProductions.wluserIdentity.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class JwtUserServiceImpl implements JwtUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserModelMapper userModelMapper;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    public static final String invalidCredentials = "INVALID_CREDENTIALS";

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    Gson gson;

    @Autowired
    AuthBusinessService authBusinessService;

    @Autowired
    ServiceUtil serviceUtil;

    @Autowired
    UserBusinessService userBusinessService;

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {

        UserDto userDto = userDao
                .findByEmailAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedFalseOrNullAndIsVerifiedTrue(email);

        if (userDto == null) {
            log.warn("User not found with username: " + email);
            throw new UsernameNotFoundException("User not found with username: " + email);
        } else {

        }

        return new User(userDto.getUserName(), userDto.getPassword(), new ArrayList<>());

    }

    @Override
    public ResponseDto authenticate(JwtRequestDto jwtRequestDto) {
        log.info("-------------JwtUserServiceImpl.authenticate(), {}--------------", jwtRequestDto);
        log.info("UserServiceImpl.authenticate() invoked.");
        ResponseDto responseDto = null;
        try {
            UserDto userDto = userBusinessService.getUserByEmail(jwtRequestDto.getEmail());
            if (userDto == null) {
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_USER_NOT_AVAILABLE_BY_EMAIL);
            } else if (!userDto.getPassword().equals(jwtRequestDto.getPassword())) {
                responseDto = serviceUtil.getErrorServiceResponse(
                        ApplicationMessageConstants.ServiceErrorMessages.ERR_PASSWORD_INVALID_FOR_EMAIL);
            } else {
                JwtResponseDto jwtResponseDto = authBusinessService.authenticate(jwtRequestDto, userDto);
                if (jwtResponseDto != null) {
                    log.info("jwt token details retrieved successfully---------{}", jwtResponseDto);
                    responseDto = serviceUtil.getServiceResponse(jwtResponseDto);
                } else {
                    log.info("Unable to retrieve jwt token details.");
                    responseDto = serviceUtil.getErrorServiceResponse(
                            ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_JWT_TOKEN);
                }
            }
        } catch (Exception e) {
            log.error("Exception occurs while retrieving jwt token details.", e);
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(
                    ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_JWT_TOKEN);
        }
        return responseDto;
    }

}
