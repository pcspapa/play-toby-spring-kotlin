package com.cspark.play.books.user

import java.sql.Connection

interface ConnectionMaker {
    fun makeNewConnection(): Connection
}