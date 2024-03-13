package com.cspark.play.books.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserDaoTest {

    @Test
    fun addAndGetNUser() { // Only test once
        val dao = NUserDao()

        dao.add(User("mj", "Mary Jane Watson", "pw"))

        val muserser: User = dao.get("mj")
        assertThat(muserser.id).isEqualTo("mj")
        assertThat(muserser.name).isEqualTo("Mary Jane Watson")
        assertThat(muserser.password).isEqualTo("pw")
    }

    @Test
    fun addAndGetDUser() { // Only test once
        val dao = DUserDao()

        dao.add(User("bp", "Brad Pitt", "pw"))

        val user = dao.get("bp")
        assertThat(user.id).isEqualTo("bp")
        assertThat(user.name).isEqualTo("Brad Pitt")
        assertThat(user.password).isEqualTo("pw")
    }
}