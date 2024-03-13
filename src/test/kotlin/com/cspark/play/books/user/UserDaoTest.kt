package com.cspark.play.books.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserDaoTest {

    @Test
    fun addAndGetNUser() { // Only test once
        val dao = UserDao(
            SimpleConnectionMaker()
        )

        dao.add(User("mj", "Mary Jane Watson", "pw"))

        val muserser: User = dao.get("mj")
        assertThat(muserser.id).isEqualTo("mj")
        assertThat(muserser.name).isEqualTo("Mary Jane Watson")
        assertThat(muserser.password).isEqualTo("pw")
    }
}