package com.cspark.play.books.user

class DaoFactory {

    fun userNDao(): UserDao {
        return UserDao(NConnectionMaker())
    }

    fun userDDao(): UserDao {
        return UserDao(DConnectionMaker())
    }
}