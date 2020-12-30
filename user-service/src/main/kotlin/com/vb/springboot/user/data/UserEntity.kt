package com.vb.springboot.user.data

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "users")
class UserEntity : Serializable {
    @Id
    @GeneratedValue
    var id: Long = 0

    @Column(nullable = false, length = 50)
    var firstName: String? = null

    @Column(nullable = false, length = 50)
    var lastName: String? = null

    @Column(nullable = false, length = 200, unique = true)
    var email: String? = null

    @Column(nullable = false, unique = true)
    var userId: String? = null

    @Column(nullable = false)
    var encryptedPassword: String? = null

    companion object {
        const val serialVersionUID = 5629489272409476609L
    }
}