package com.cspark.play.books.user.dao

import com.cspark.play.books.user.domain.User
import org.springframework.dao.EmptyResultDataAccessException
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

class UserDao(
    private val dataSource: DataSource,
) {

    private val jdbcContext = JdbcContext(dataSource)

    fun add(user: User) {
        jdbcContext.workWithStatementStrategy(
            object : StatementStrategy {
                override fun makePreparedStatement(c: Connection): PreparedStatement {
                    val ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)")

                    ps.setString(1, user.id);
                    ps.setString(2, user.name);
                    ps.setString(3, user.password);

                    return ps
                }
            }
        )
    }

    fun add2(user: User) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("insert into users(id, name, password) values(?, ?, ?)").use { preparedStatement ->
                preparedStatement.setString(1, user.id)
                preparedStatement.setString(2, user.name)
                preparedStatement.setString(3, user.password)
                preparedStatement.executeUpdate()
            }
        }
    }

    fun get(id: String): User {
        var c: Connection? = null
        var ps: PreparedStatement? = null
        var rs: ResultSet? = null

        try {
            c = dataSource.connection
            ps = c.prepareStatement("select * from users where id = ?")
            ps.setString(1, id)
            rs = ps.executeQuery()

            return if (rs.next()) {
                User(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password")
                )
            } else throw EmptyResultDataAccessException(1)
        } catch (e: Exception) {
            throw e
        } finally {
            try { rs?.close() } catch (_: Exception) { }
            try { ps?.close() } catch (_: Exception) { }
            try { c?.close() } catch (_: Exception) { }
        }
    }

    fun get2(id: String): User {
        dataSource.connection.use { connection ->
            connection.prepareStatement("select * from users where id = ?").use { preparedStatement ->
                preparedStatement.setString(1, id)
                preparedStatement.executeQuery().use { resultSet ->
                    return if (resultSet.next()) {
                        User(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("password")
                        )
                    } else {
                        throw EmptyResultDataAccessException(1)
                    }
                }
            }
        }
    }

    fun deleteAll() {
        jdbcContext.workWithStatementStrategy(
            object : StatementStrategy {
                override fun makePreparedStatement(c: Connection): PreparedStatement {
                    return c.prepareStatement("delete from users")
                }
            }
        )
    }

    fun deleteAll2() {
        dataSource.connection.use { connection ->
            connection.prepareStatement("delete from users").use { preparedStatement ->
                preparedStatement.executeUpdate()
            }
        }
    }

    fun getCount(): Int {
        var c: Connection? = null
        var ps: PreparedStatement? = null
        var rs: ResultSet? = null

        try {
            c = dataSource.connection
            ps = c.prepareStatement("select count(*) from users")

            rs = ps.executeQuery()
            rs.next()

            return rs.getInt(1)
        } catch (e: Exception) {
            throw e
        } finally {
            try { rs?.close() } catch (_: Exception) { }
            try { ps?.close() } catch (_: Exception) { }
            try { c?.close() } catch (_: Exception) { }
        }
    }

    fun getCount2(): Int {
        dataSource.connection.use { connection ->
            connection.prepareStatement("select count(*) from users").use { preparedStatement ->
                preparedStatement.executeQuery().use { resultSet ->
                    return if (resultSet.next()) {
                        resultSet.getInt(1)
                    } else {
                        0
                    }
                }
            }
        }
    }
}