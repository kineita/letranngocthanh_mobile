package com.letranngocthanh.presentation.feature.user_detail

import com.letranngocthanh.domain.model.user.UserDetail
import com.letranngocthanh.presentation.model.users.UserDetailUI
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultUserDetailMapperTest {

    private lateinit var userDetailMapper: DefaultUserDetailMapper

    @Before
    fun setUp() {
        userDetailMapper = DefaultUserDetailMapper()
    }

    @Test
    fun `toUserDetailUI should map UserDetail to UserDetailUI`() {
        // Arrange
        val userDetail = UserDetail(
            login = "testUser",
            avatar_url = "avatarUrl",
            html_url = "htmlUrl",
            location = "testLocation",
            followers = 100,
            following = 50
        )
        val expectedUserDetailUI = UserDetailUI(
            login = "testUser",
            avatar_url = "avatarUrl",
            html_url = "htmlUrl",
            location = "testLocation",
            followers = 100,
            following = 50
        )

        // Act
        val userDetailUI = userDetailMapper.toUserDetailUI(userDetail)

        // Assert
        assertEquals(expectedUserDetailUI, userDetailUI)
    }
}