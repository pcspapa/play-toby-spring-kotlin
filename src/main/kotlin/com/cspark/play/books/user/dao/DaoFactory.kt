package com.cspark.play.books.user.dao

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource


@Configuration
class DaoFactory {

    @Bean
    fun dataSource(): DataSource {
        val dataSource = SimpleDriverDataSource()
        dataSource.setDriverClass(org.h2.Driver::class.java)
        dataSource.url = "jdbc:h2:tcp://localhost/~/toby-spring"
        dataSource.username = "sa"
        dataSource.password = ""

        return dataSource
    }

    @Bean
    fun userDao(): UserDao {
        return UserDao(dataSource())
    }
}