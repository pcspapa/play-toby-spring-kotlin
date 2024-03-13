package com.cspark.play.books.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class UserDaoTest {

    @Test
    fun addAndGetNUser() { // Only test once
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao = context.getBean("userNDao", UserDao::class.java)

        dao.add(User("mj", "Mary Jane Watson", "pw"))

        val user: User = dao.get("mj")
        assertThat(user.id).isEqualTo("mj")
        assertThat(user.name).isEqualTo("Mary Jane Watson")
        assertThat(user.password).isEqualTo("pw")
    }

    @Test
    fun addAndGetDUser() { // Only test once
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao = context.getBean("userDDao", UserDao::class.java)

        dao.add(User("bp", "Brad Pitt", "pw"))

        val user = dao.get("bp")
        assertThat(user.id).isEqualTo("bp")
        assertThat(user.name).isEqualTo("Brad Pitt")
        assertThat(user.password).isEqualTo("pw")
    }
}