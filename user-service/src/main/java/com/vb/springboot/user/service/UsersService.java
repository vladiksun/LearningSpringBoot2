package com.vb.springboot.user.service;

import com.vb.springboot.user.shared.UserDto;
import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.response.UserRestResponse;

public interface UsersService {

    UserDto createUser(UserDto userDetails);

    UserRestResponse getUser(String userId);

    UserRestResponse updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate);

    void deleteUser(String userId);
}


