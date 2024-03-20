package com.cspark.play.books.user.dao

import com.cspark.play.books.user.domain.User
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import java.sql.ResultSet
import javax.sql.DataSource


class UserDao(
    private val dataSource: DataSource,
) {

    private val jdbcTemplate = JdbcTemplate(dataSource)
    private val jdbcContext = JdbcContext(dataSource)

    val userMapper: (rs: ResultSet, rowNum: Int) -> User = { rs, _ ->
        User(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("password")
        )
    }

    fun add(user: User) {
        jdbcTemplate.update(
            "insert into users(id, name, password) values(?,?,?)",
            user.id, user.name, user.password
        )
    }

    fun add2(user: User) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("insert into users(id, name, password) values(?, ?, ?)")
                .use { preparedStatement ->
                    preparedStatement.setString(1, user.id)
                    preparedStatement.setString(2, user.name)
                    preparedStatement.setString(3, user.password)
                    preparedStatement.executeUpdate()
                }
        }
    }

    fun get(id: String): User {
        return jdbcTemplate.queryForObject("select * from users where id = ?", userMapper, id)
            ?: throw EmptyResultDataAccessException(1)
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

    fun getAll(): List<User> {
        return jdbcTemplate.query("select * from users order by id", userMapper)
    }

    fun deleteAll() {
        jdbcTemplate.update("delete from users")
    }

    fun deleteAll2() {
        dataSource.connection.use { connection ->
            connection.prepareStatement("delete from users").use { preparedStatement ->
                preparedStatement.executeUpdate()
            }
        }
    }

    fun getCount(): Int {
        return jdbcTemplate.queryForObject("select count(*) from users", Int::class.java) ?: 0

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