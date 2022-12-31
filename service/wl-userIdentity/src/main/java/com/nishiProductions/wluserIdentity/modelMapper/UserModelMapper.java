package com.nishiProductions.wluserIdentity.modelMapper;

import com.nishiProductions.wluserIdentity.domain.User;
import com.nishiProductions.wluserIdentity.domain.UserType;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import com.nishiProductions.wluserIdentity.dto.UserTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserTypeModelMapper userTypeModelMapper;

    public UserDto userToUserDto(User user) {
        UserDto mappedUserDto = modelMapper.map(user, UserDto.class);
        if (user.getUserType() != null) {
            UserTypeDto userTypeDto = userTypeModelMapper.userTypeToUserTypeDto(user.getUserType());
            mappedUserDto.setUserTypeDto(userTypeDto);
        }
        return mappedUserDto;
    }

    public User userDtoToUser(UserDto userDto) {
        User mappedUser = modelMapper.map(userDto, User.class);
        if (userDto.getUserTypeDto() != null) {
            UserType userType = userTypeModelMapper.userTypeDtoToUserType(userDto.getUserTypeDto());
            mappedUser.setUserType(userType);
        }
        return mappedUser;

    }

}
