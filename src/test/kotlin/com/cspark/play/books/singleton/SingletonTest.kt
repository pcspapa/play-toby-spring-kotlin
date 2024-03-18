package com.cspark.play.books.singleton

import com.cspark.play.books.user.dao.DaoFactory
import com.cspark.play.books.user.dao.UserDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {

    @Test
    fun factoryObject() {
        val factory = DaoFactory()
        val dao1 = factory.userDao()
        val dao2 = factory.userDao()

        println("dao1 = $dao1")
        println("dao2 = $dao2")

        assertThat(dao1).isNotEqualTo(dao2)
    }

    @Test
    fun springBean() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao1 = context.getBean("userDao", UserDao::class.java)
        val dao2 = context.getBean("userDao", UserDao::class.java)

        println("dao1 = $dao1")
        println("dao2 = $dao2")

        assertThat(dao1).isEqualTo(dao2)
    }
}