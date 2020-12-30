package com.vb.springboot.user.service.impl

import org.springframework.beans.factory.annotation.Autowired
import com.vb.springboot.user.data.UsersRepository
import org.modelmapper.ModelMapper
import com.vb.springboot.user.service.UsersService
import com.vb.springboot.user.shared.UserDto
import com.vb.springboot.user.data.UserEntity
import com.vb.springboot.user.utils.Utils
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl @Autowired constructor(private val usersRepository: UsersRepository,
                                              private val utils: Utils,
                                              private val modelMapper: ModelMapper) : UsersService {

    override fun createUser(userDetails: UserDto): UserDto {
        userDetails.userId = utils.generateUserId()
        val userEntity = modelMapper.map(userDetails, UserEntity::class.java)
        userEntity.encryptedPassword = "test"
        usersRepository.save(userEntity)
        return userDetails
    }

    override fun getUser(userId: String): UserDto? {
        TODO()
    }

    override fun updateUser(userId: String, userToUpdate: UserDto): UserDto? {
        TODO()
    }

    override fun deleteUser(userId: String) {
        TODO()
    }
}