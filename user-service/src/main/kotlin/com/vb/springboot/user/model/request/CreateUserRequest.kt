package com.vb.springboot.user.model.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateUserRequest(
        @field:NotNull(message = "First name cannot be null")
        @field:Size(min = 2, message = "First name must not be less than two characters")
        var firstName: String?,

        @field:NotNull
        var lastName: String?,

        @field:Email
        var email: String?,

        @field:NotNull
        var password: String?,
)

