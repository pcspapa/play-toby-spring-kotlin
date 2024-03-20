package com.cspark.play.books.user.dao

import com.cspark.play.books.user.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationContext
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [DaoFactory::class])
class UserDaoTest(
    private val context: ApplicationContext
) {

    private lateinit var dao: UserDao

    private lateinit var user1: User
    private lateinit var user2: User
    private lateinit var user3: User

    @BeforeEach
    fun setUp() {
        dao = context.getBean("userDao", UserDao::class.java)

        user1 = User("mj", "Mary Jane Watson", "pw")
        user2 = User("bp", "Brad Pitt", "pw")
        user3 = User("papa", "Chanseok Park", "pw")
    }

    @Test
    fun addAndGet() {
        dao.deleteAll()
        assertThat(dao.getCount()).isEqualTo(0)

        dao.add(user1)
        assertThat(dao.getCount()).isEqualTo(1)

        val findUser1: User = dao.get("mj")
        assertThat(findUser1.id).isEqualTo("mj")
        assertThat(findUser1.name).isEqualTo("Mary Jane Watson")
        assertThat(findUser1.password).isEqualTo("pw")

        dao.add(user2)
        assertThat(dao.getCount()).isEqualTo(2)

        val findUser2 = dao.get("bp")
        assertThat(findUser2.id).isEqualTo("bp")
        assertThat(findUser2.name).isEqualTo("Brad Pitt")
        assertThat(findUser2.password).isEqualTo("pw")
    }

    @Test
    fun count() {
        dao.deleteAll()
        assertThat(dao.getCount()).isEqualTo(0)

        dao.add(user1)
        assertThat(dao.getCount()).isEqualTo(1)

        dao.add(user2)
        assertThat(dao.getCount()).isEqualTo(2)

        dao.add(user3)
        assertThat(dao.getCount()).isEqualTo(3)
    }

    @Test
    fun getUserFailure() {
        assertThatThrownBy { dao.get("unknown") }
            .isInstanceOf(EmptyResultDataAccessException::class.java)
    }

    @Test
    fun getAll() {
        dao.deleteAll()

        val users0 = dao.getAll()
        assertThat(dao.getCount()).isEqualTo(0)

        dao.add(user1) // id: mj
        val users1 = dao.getAll()
        assertThat(dao.getCount()).isEqualTo(1)
        checkSameUser(user1, users1[0])

        dao.add(user2) // id: bp
        val users2 = dao.getAll()
        assertThat(dao.getCount()).isEqualTo(2)
        checkSameUser(user1, users2[1])
        checkSameUser(user2, users2[0])

        dao.add(user3) // id: papa
        val users3 = dao.getAll()
        assertThat(dao.getCount()).isEqualTo(3)
        checkSameUser(user1, users3[1])
        checkSameUser(user2, users3[0])
        checkSameUser(user3, users3[2])
    }

    private fun checkSameUser(user1: User, user2: User) {
        assertThat(user1.id).isEqualTo(user2.id)
        assertThat(user1.name).isEqualTo(user2.name)
        assertThat(user1.password).isEqualTo(user2.password)
    }
}

