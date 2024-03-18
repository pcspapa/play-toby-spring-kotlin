package com.cspark.play.books.user.dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import javax.sql.DataSource

class JdbcContext(
    private val dataSource: DataSource,
) {

    @Throws(SQLException::class)
    fun workWithStatementStrategy(stmt: StatementStrategy) {
        var c: Connection? = null
        var ps: PreparedStatement? = null

        try {
            c = dataSource.connection
            ps = stmt.makePreparedStatement(c)

            ps.executeUpdate()
        } catch (e: SQLException) {
            throw e
        } finally {
            try { ps?.close() } catch (_: Exception) { }
            try { c?.close() } catch (_: Exception) { }
        }
    }
}