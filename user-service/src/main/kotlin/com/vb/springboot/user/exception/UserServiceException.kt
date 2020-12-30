package com.vb.springboot.user.exception

import java.lang.RuntimeException

class UserServiceException(message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 3300122125227502449L
    }
}