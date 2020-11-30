package com.vb.springboot.webservice.service;

import com.vb.springboot.webservice.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.webservice.model.request.UserDetailsRequestModel;
import com.vb.springboot.webservice.model.response.UserRestResponse;

public interface UserService {

    UserRestResponse createUser(UserDetailsRequestModel user);

    UserRestResponse getUser(String userId);

    UserRestResponse updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate);

    void deleteUser(String userId);
}


