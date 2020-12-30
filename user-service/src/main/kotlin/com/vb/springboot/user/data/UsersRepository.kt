package com.vb.springboot.user.data

import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<UserEntity?, Long?>