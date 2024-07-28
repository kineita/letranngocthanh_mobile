package com.letranngocthanh.presentation.feature.users

import com.letranngocthanh.domain.model.user.User
import com.letranngocthanh.presentation.model.users.UserUI
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultUserUIMapperTest {

    private lateinit var userUIMapper: DefaultUserUIMapper

    @Before
    fun setUp() {
        userUIMapper = DefaultUserUIMapper()
    }

    @Test
    fun `toUserUI should map User to UserUI`() {
        // Arrange
        val user = User(login = "testUser", avatar_url = "avatarUrl", html_url = "htmlUrl")
        val expectedUserUI = UserUI(login = "testUser", avatar_url = "avatarUrl", html_url = "htmlUrl")

        // Act
        val userUI = userUIMapper.toUserUI(user)

        // Assert
        assertEquals(expectedUserUI, userUI)
    }

    @Test
    fun `toUserUIs should map list of Users to list of UserUIs`() {
        // Arrange
        val users = listOf(
            User(login = "testUser1", avatar_url = "avatarUrl1", html_url = "htmlUrl1"),
            User(login = "testUser2", avatar_url = "avatarUrl2", html_url = "htmlUrl2")
        )
        val expectedUserUIs = listOf(
            UserUI(login = "testUser1", avatar_url = "avatarUrl1", html_url = "htmlUrl1"),
            UserUI(login = "testUser2", avatar_url = "avatarUrl2", html_url = "htmlUrl2")
        )

        // Act
        val userUIs = userUIMapper.toUserUIs(users)

        // Assert
        assertEquals(expectedUserUIs, userUIs)
    }
}