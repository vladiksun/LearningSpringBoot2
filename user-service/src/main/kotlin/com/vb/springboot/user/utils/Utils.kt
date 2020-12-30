package com.vb.springboot.user.utils

import org.springframework.stereotype.Component
import java.util.UUID

@Component
class Utils {
    fun generateUserId(): String {
        return UUID.randomUUID().toString()
    }
}