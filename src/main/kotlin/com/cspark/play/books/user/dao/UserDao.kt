package com.cspark.play.books.user.dao

import com.cspark.play.books.user.domain.User
import org.springframework.dao.EmptyResultDataAccessException
import javax.sql.DataSource

class UserDao(
    private val dataSource: DataSource,
) {

    fun add(user: User) {
        val c = dataSource.connection
        val ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)")

        ps.setString(1, user.id);
        ps.setString(2, user.name);
        ps.setString(3, user.password);
        ps.executeUpdate();
        ps.close()
        c.close()
    }

    fun get(id: String): User {
        val c = dataSource.connection
        val ps = c.prepareStatement("select * from users where id = ?")
        ps.setString(1, id)

        val rs = ps.executeQuery()

        return if (rs.next()) {
            User(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("password")
            )
        } else throw EmptyResultDataAccessException(1)
    }

    fun deleteAll() {
        val c = dataSource.connection
        val ps = c.prepareStatement("delete from users")

        ps.executeUpdate()

        ps.close()
        c.close()
    }

    fun getCount(): Int {
        val c = dataSource.connection
        val ps = c.prepareStatement("select count(*) from users")

        val rs = ps.executeQuery()
        rs.next()
        val count = rs.getInt(1)

        ps.close()
        c.close()

        return count
    }
}