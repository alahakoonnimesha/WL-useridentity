package com.nishiProductions.wluserIdentity.modelMapper;

import com.nishiProductions.wluserIdentity.domain.UserType;
import com.nishiProductions.wluserIdentity.dto.UserTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTypeModelMapper {

    @Autowired
    ModelMapper modelMapper;

    public UserTypeDto userTypeToUserTypeDto(UserType userType) {
        return modelMapper.map(userType, UserTypeDto.class);
    }

    public UserType userTypeDtoToUserType(UserTypeDto userTypeDto) {
        return modelMapper.map(userTypeDto, UserType.class);

    }
}
