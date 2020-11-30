package com.vb.springboot.user.service;

import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.request.UserDetailsRequestModel;
import com.vb.springboot.user.model.response.UserRestResponse;

public interface UsersService {

    UserRestResponse createUser(UserDetailsRequestModel user);

    UserRestResponse getUser(String userId);

    UserRestResponse updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate);

    void deleteUser(String userId);
}


