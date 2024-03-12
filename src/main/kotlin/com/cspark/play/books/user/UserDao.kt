package com.cspark.play.books.user

import java.sql.DriverManager

class UserDao {

    fun add(user: User) {
        Class.forName("org.h2.Driver")
        val c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "")
        val ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)")

        ps.setString(1, user.id);
        ps.setString(2, user.name);
        ps.setString(3, user.password);
        ps.executeUpdate();
        ps.close()
        c.close()
    }

    fun get(id: String): User {
        Class.forName("org.h2.Driver")
        val c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/toby-spring", "sa", "")
        val ps = c.prepareStatement("select * from users where id = ?")
        ps.setString(1, id)

        val rs = ps.executeQuery()
        rs.next()

        return User(
            rs.getString("id"),
            rs.getString("name"),
            rs.getString("password")
        )
    }
}

fun main(args: Array<String>) {
    val userDao = UserDao()

    val user = User("papa", "cspark", "password")
    userDao.add(user)

    println("${user.id} - Creation successful!")

    val findUser = userDao.get(user.id)
    println(findUser.name)
    println(findUser.password)
    println("${user.id} - Selection successful!")
}