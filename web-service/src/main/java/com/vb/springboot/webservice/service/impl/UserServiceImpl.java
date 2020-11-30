package com.vb.springboot.webservice.service.impl;

import com.vb.springboot.webservice.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.webservice.model.request.UserDetailsRequestModel;
import com.vb.springboot.webservice.model.response.UserRestResponse;
import com.vb.springboot.webservice.service.UserService;
import com.vb.springboot.webservice.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRestResponse> users;

    private final Utils utils;

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @PostConstruct
    private void init() {
        users = new ConcurrentHashMap<>();
    }

    @Override
    public UserRestResponse createUser(UserDetailsRequestModel user) {
        String userID = utils.generateUserId();

        UserRestResponse userRestResponse = new UserRestResponse();
        userRestResponse.setFirstName(user.getFirstName());
        userRestResponse.setLastName(user.getLastName());
        userRestResponse.setEmail(user.getEmail());
        userRestResponse.setPassword(user.getPassword());
        userRestResponse.setUserId(userID);

        users.put(userID, userRestResponse);

        return userRestResponse;
    }

    @Override
    public UserRestResponse getUser(String userId) {
        return users.getOrDefault(userId, null);
    }

    @Override
    public UserRestResponse updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate) {
        UserRestResponse storedUser = users.get(userId);
        storedUser.setFirstName(userToUpdate.getFirstName());
        storedUser.setLastName(userToUpdate.getLastName());

        users.put(userId, storedUser);

        return storedUser;
    }

    @Override
    public void deleteUser(String userId) {
        users.remove(userId);
    }
}
