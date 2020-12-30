package com.vb.springboot.user.service;

import com.vb.springboot.user.shared.UserDto;
import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.response.CreateUserResponse;

public interface UsersService {

    UserDto createUser(UserDto userDetails);

    UserDto getUser(String userId);

    UserDto updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate);

    void deleteUser(String userId);
}


