package com.cspark.play.books.user.dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException


interface StatementStrategy {

    @Throws(SQLException::class)
    fun makePreparedStatement(c: Connection): PreparedStatement
}