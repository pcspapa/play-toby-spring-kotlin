package com.cspark.play.books.user.dao

import java.sql.Connection
import java.sql.PreparedStatement

class DeleteAllStatement : StatementStrategy {

    override fun makePreparedStatement(c: Connection): PreparedStatement {
        return c.prepareStatement("delete from users")
    }
}