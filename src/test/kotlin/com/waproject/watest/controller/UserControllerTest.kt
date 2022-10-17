package com.waproject.watest.controller

import com.waproject.watest.model.User
import com.waproject.watest.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.*
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

internal class UserControllerTest {

    @MockK
    private lateinit var repository: UserRepository

    @InjectMockKs
    private lateinit var controller: UserController

    @BeforeTest
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun get() {

        val id = 1
        val user = mockk<User>()
        val optinalUser = Optional.of(user)

        every { repository.findById(id) } returns optinalUser

        val response = controller.get(id)

        verify { repository.findById(id) }
        assertEquals(HttpStatus.OK.value(), response.statusCode.value())

    }

    @Test
    fun save() {
        val user = mockk<User>()

        every { repository.save(any()) } returns mockk()
        val response = controller.save(user)

        assertEquals(HttpStatus.CREATED.value(), response.statusCode.value())
    }

    @Test
    fun update() {
        val id = 1
        val user = mockk<User>(relaxed = true)
        val optinalUser = Optional.of(user)

        every { repository.findById(id) } returns optinalUser
        every { repository.save(any()) } returns user

        val response = controller.update(id, user)

        assertEquals(HttpStatus.OK.value(), response.statusCode.value())
    }

}
