package com.vb.springboot.user.service.impl;

import com.vb.springboot.user.data.UserEntity;
import com.vb.springboot.user.data.UsersRepository;
import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.response.CreateUserResponse;
import com.vb.springboot.user.service.UsersService;
import com.vb.springboot.user.shared.UserDto;
import com.vb.springboot.user.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final Utils utils;

    private final ModelMapper modelMapper;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, Utils utils, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.utils = utils;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(utils.generateUserId());

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword("test");

        usersRepository.save(userEntity);

        return userDetails;
    }

    @Override
    public UserDto getUser(String userId) {
        //return users.getOrDefault(userId, null);

        return null;
    }

    @Override
    public UserDto updateUser(String userId, UpdateUserDetailsRequestModel userToUpdate) {
//        UserRestResponse storedUser = users.get(userId);
//        storedUser.setFirstName(userToUpdate.getFirstName());
//        storedUser.setLastName(userToUpdate.getLastName());
//
//        users.put(userId, storedUser);
//
//        return storedUser;

        return null;
    }

    @Override
    public void deleteUser(String userId) {
        //users.remove(userId);
    }
}
