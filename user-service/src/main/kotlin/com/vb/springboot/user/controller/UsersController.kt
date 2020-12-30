package com.vb.springboot.user.controller

import com.vb.springboot.user.model.request.CreateUserRequest
import com.vb.springboot.user.model.request.UpdateUserDetailsRequestModel
import com.vb.springboot.user.model.response.CreateUserResponse
import com.vb.springboot.user.service.UsersService
import com.vb.springboot.user.shared.UserDto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersController @Autowired constructor(private val usersService: UsersService,
                                             private val environment: Environment,
                                             private val modelMapper: ModelMapper) {

    fun getUsers(@RequestParam(value = "page", defaultValue = "1") page: Int,
                 @RequestParam(value = "limit", defaultValue = "50") limit: Int,
                 @RequestParam(value = "sort", defaultValue = "desc", required = false) sort: String): String {
        return "get users was called with page = $page and limit = $limit and sort = $sort"
    }

    @GetMapping(path = ["/{userId}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun getUser(@PathVariable userId: String): ResponseEntity<CreateUserResponse> {
        val user = usersService.getUser(userId)
                ?: return ResponseEntity(HttpStatus.NO_CONTENT)
        return ResponseEntity(modelMapper.map(user, CreateUserResponse::class.java), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
                 produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun createUser(@Valid @RequestBody user: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val userDto = modelMapper.map(user, UserDto::class.java)
        val savedUser = usersService.createUser(userDto)
        return ResponseEntity(modelMapper.map(savedUser, CreateUserResponse::class.java), HttpStatus.OK)
    }

    @PutMapping(path = ["/{userId}"],
            consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun updateUser(@PathVariable userId: String,
                   @RequestBody userToUpdate: UpdateUserDetailsRequestModel): ResponseEntity<CreateUserResponse> {
        val userDto = modelMapper.map(userToUpdate, UserDto::class.java)

        val updatedUser = usersService.updateUser(userId,  userDto)
                ?: return ResponseEntity(HttpStatus.NO_CONTENT)
        return ResponseEntity(modelMapper.map(updatedUser, CreateUserResponse::class.java), HttpStatus.CREATED)
    }

    @DeleteMapping(path = ["/{userId}"])
    fun deleteUser(@PathVariable userId: String): ResponseEntity<Void> {
        usersService.deleteUser(userId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/status/check")
    fun status(): String {
        return "Working ))) Hello from USERS-WS PORT: " + environment.getProperty("local.server.port")
    }
}