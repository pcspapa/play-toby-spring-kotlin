package com.cspark.play.books.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class UserDaoTest {

    @Test
    fun addAndGet() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao = context.getBean("userDao", UserDao::class.java)

        dao.deleteAll()
        assertThat(dao.getCount()).isEqualTo(0)

        val user1 = User("mj", "Mary Jane Watson", "pw")
        val user2 = User("bp", "Brad Pitt", "pw")

        dao.add(user1)
        assertThat(dao.getCount()).isEqualTo(1)

        val findUser1: User = dao.get("mj")
        assertThat(findUser1.id).isEqualTo("mj")
        assertThat(findUser1.name).isEqualTo("Mary Jane Watson")
        assertThat(findUser1.password).isEqualTo("pw")

        dao.add(user2)
        assertThat(dao.getCount()).isEqualTo(2)

        val findUser2 = dao.get("bp")
        assertThat(findUser2.id).isEqualTo("bp")
        assertThat(findUser2.name).isEqualTo("Brad Pitt")
        assertThat(findUser2.password).isEqualTo("pw")
    }

    @Test
    fun count() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao = context.getBean("userDao", UserDao::class.java)

        dao.deleteAll()
        assertThat(dao.getCount()).isEqualTo(0)

        val user1 = User("mj", "Mary Jane Watson", "pw")
        val user2 = User("bp", "Brad Pitt", "pw")
        val user3 = User("papa", "Chanseok Park", "pw")

        dao.add(user1)
        assertThat(dao.getCount()).isEqualTo(1)

        dao.add(user2)
        assertThat(dao.getCount()).isEqualTo(2)

        dao.add(user3)
        assertThat(dao.getCount()).isEqualTo(3)
    }
}