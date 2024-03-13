package com.cspark.play.books.user

class UserDao(
    private val connectionMaker: ConnectionMaker,
) {

    fun add(user: User) {
        val c = connectionMaker.makeNewConnection()
        val ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)")

        ps.setString(1, user.id);
        ps.setString(2, user.name);
        ps.setString(3, user.password);
        ps.executeUpdate();
        ps.close()
        c.close()
    }

    fun get(id: String): User {
        val c = connectionMaker.makeNewConnection()
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