package com.cspark.play.books.user

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DaoFactory {

    @Bean
    fun userNDao(): UserDao {
        return UserDao(NConnectionMaker())
    }

    @Bean
    fun userDDao(): UserDao {
        return UserDao(DConnectionMaker())
    }
}