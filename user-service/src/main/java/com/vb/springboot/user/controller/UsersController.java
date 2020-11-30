package com.vb.springboot.user.controller;

import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel;
import com.vb.springboot.user.model.request.UserDetailsRequestModel;
import com.vb.springboot.user.model.response.UserRestResponse;
import com.vb.springboot.user.service.UsersService;
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

    private Environment environment;

    @Autowired
    public UsersController(final UsersService usersService, final Environment environment) {
        this.usersService = usersService;
        this.environment = environment;
    }

    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = {"/{userId}"},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> getUser(@PathVariable String userId) {
        UserRestResponse userRestResponse = usersService.getUser(userId);

        if (userRestResponse == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(userRestResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> createUser(@Valid @RequestBody UserDetailsRequestModel user) {
        UserRestResponse userRestResponse = usersService.createUser(user);
        return new ResponseEntity<>(userRestResponse, HttpStatus.OK);
    }

    @PutMapping(path = {"/{userId}"},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UserRestResponse> updateUser(@PathVariable String userId,
                                                       @RequestBody UpdateUserDetailsRequestModel userToUpdate) {
        UserRestResponse storedUser = usersService.updateUser(userId, userToUpdate);
        return new ResponseEntity<>(storedUser, HttpStatus.OK);
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
