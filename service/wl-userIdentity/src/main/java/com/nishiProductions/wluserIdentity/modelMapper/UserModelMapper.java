package com.nishiProductions.wluserIdentity.modelMapper;

import com.nishiProductions.wluserIdentity.domain.User;
import com.nishiProductions.wluserIdentity.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    @Autowired
    ModelMapper modelMapper;

    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);

    }

}
