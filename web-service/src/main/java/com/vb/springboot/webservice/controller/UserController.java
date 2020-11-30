package com.vb.springboot.webservice.controller;

import com.vb.springboot.webservice.exception.UserServiceException;
import com.vb.springboot.webservice.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.webservice.model.request.UserDetailsRequestModel;
import com.vb.springboot.webservice.model.response.UserRestResponse;
import com.vb.springboot.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = {"/{userId}"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> getUser(@PathVariable String userId) {
        UserRestResponse userRestResponse = userService.getUser(userId);

        if (userRestResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userRestResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> createUser(@Valid @RequestBody UserDetailsRequestModel user) {
        UserRestResponse userRestResponse = userService.createUser(user);
        return new ResponseEntity<>(userRestResponse, HttpStatus.OK);
    }

    @PutMapping(path = {"/{userId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> updateUser(@PathVariable String userId,
                                                       @RequestBody UpdateUserDetailsRequestModel userToUpdate) {
        UserRestResponse storedUser = userService.updateUser(userId, userToUpdate);
        return new ResponseEntity<>(storedUser, HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{userId}"})
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }

}
