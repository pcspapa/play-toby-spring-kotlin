package com.cspark.play.books.singleton

import com.cspark.play.books.user.DaoFactory
import com.cspark.play.books.user.UserDao
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest {

    @Test
    fun factoryObject() {
        val factory = DaoFactory()
        val dao1 = factory.userNDao()
        val dao2 = factory.userNDao()

        println("dao1 = $dao1")
        println("dao2 = $dao2")

        assertThat(dao1).isNotEqualTo(dao2)
    }

    @Test
    fun springBean() {
        val context: ApplicationContext = AnnotationConfigApplicationContext(DaoFactory::class.java)
        val dao1 = context.getBean("userNDao", UserDao::class.java)
        val dao2 = context.getBean("userNDao", UserDao::class.java)

        println("dao1 = $dao1")
        println("dao2 = $dao2")

        assertThat(dao1).isEqualTo(dao2)
    }
}