package com.vb.springboot.user.service.impl;

import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.request.UserDetailsRequestModel;
import com.vb.springboot.user.model.response.UserRestResponse;
import com.vb.springboot.user.service.UsersService;
import com.vb.springboot.user.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsersServiceImpl implements UsersService {

    Map<String, UserRestResponse> users;

    private final Utils utils;

    @Autowired
    public UsersServiceImpl(Utils utils) {
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
