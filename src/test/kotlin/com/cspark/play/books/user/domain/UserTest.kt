package com.cspark.play.books.user.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun createUser() {
        val user = User("id", "name", "password")

        assertThat(user).isNotNull()
        assertThat(user.id).isEqualTo("id")
        assertThat(user.name).isEqualTo("name")
        assertThat(user.password).isEqualTo("password")
    }

    @Test
    fun equalAndHash() {
        val user = User("id", "name", "password")
        val renameUser = User("id", "rename", "password")

        assertThat(user).isEqualTo(renameUser)
    }
}