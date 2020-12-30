package com.vb.springboot.user.service

import com.vb.springboot.user.shared.UserDto

interface UsersService {
    fun createUser(userDetails: UserDto): UserDto

    fun getUser(userId: String): UserDto?

    fun updateUser(userId: String, userToUpdate: UserDto): UserDto?

    fun deleteUser(userId: String)
}