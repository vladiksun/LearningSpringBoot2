package com.vb.springboot.user.shared

import java.io.Serializable

class UserDto : Serializable {
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var password: String? = null
    var userId: String? = null
    var encryptedPassword: String? = null

    companion object {
        private const val serialVersionUID = -8452662598509341123L
    }
}