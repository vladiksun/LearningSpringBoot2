package com.vb.springboot.user.controller;

import com.vb.springboot.user.shared.UserDto;
import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.request.CreateUserRequest;
import com.vb.springboot.user.model.response.CreateUserResponse;
import com.vb.springboot.user.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    private final Environment environment;

    private final ModelMapper modelMapper;


    @Autowired
    public UsersController(final UsersService usersService,
                           final Environment environment,
                           final ModelMapper modelMapper) {
        this.usersService = usersService;
        this.environment = environment;
        this.modelMapper = modelMapper;
    }

    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = {"/{userId}"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponse> getUser(@PathVariable String userId) {
        UserDto user = usersService.getUser(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modelMapper.map(user, CreateUserResponse.class), HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                 produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        UserDto savedUser = usersService.createUser(userDto);

        return new ResponseEntity<>(modelMapper.map(savedUser, CreateUserResponse.class), HttpStatus.OK);
    }

    @PutMapping(path = {"/{userId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponse> updateUser(@PathVariable String userId,
                                                         @RequestBody UpdateUserDetailsRequestModel userToUpdate) {
        UserDto updatedUser = usersService.updateUser(userId, userToUpdate);
        return new ResponseEntity<>(modelMapper.map(updatedUser, CreateUserResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/{userId}"})
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        usersService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working ))) Hello from USERS-WS PORT: " + environment.getProperty("local.server.port");
    }

}