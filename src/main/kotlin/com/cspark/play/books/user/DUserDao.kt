package com.cspark.play.books.user

import java.sql.Connection
import java.sql.DriverManager

class DUserDao : UserDao() {

    override fun getConnection(): Connection {
        Class.forName("org.h2.Driver")
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "")
    }
}