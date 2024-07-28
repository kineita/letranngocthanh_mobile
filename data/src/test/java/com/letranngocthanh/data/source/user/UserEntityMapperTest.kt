package com.letranngocthanh.data.source.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDetailEntity
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.model.UserEntity
import com.letranngocthanh.data.source.user.local.toDto
import com.letranngocthanh.data.source.user.local.toEntity
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserEntityMapperTest {

    @Test
    fun `UserDto to UserEntity conversion`() {
        val userDto = UserDto("user1", "url1", "html1")
        val userEntity = userDto.toEntity(0)

        assertEquals("user1", userEntity.login)
        assertEquals("url1", userEntity.avatar_url)
        assertEquals("html1", userEntity.html_url)
        assertEquals(0, userEntity.orderIndex)
    }

    @Test
    fun `UserEntity to UserDto conversion`() {
        val userEntity = UserEntity("user1", "url1", "html1", 0)
        val userDto = userEntity.toDto()

        assertEquals("user1", userDto.login)
        assertEquals("url1", userDto.avatar_url)
        assertEquals("html1", userDto.html_url)
    }

    @Test
    fun `UserDetailDto to UserDetailEntity conversion`() {
        val userDetailDto = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        val userDetailEntity = userDetailDto.toEntity()

        assertEquals("user1", userDetailEntity.login)
        assertEquals("url1", userDetailEntity.avatar_url)
        assertEquals("html1", userDetailEntity.html_url)
        assertEquals("location", userDetailEntity.location)
        assertEquals(100, userDetailEntity.followers)
        assertEquals(200, userDetailEntity.following)
    }

    @Test
    fun `UserDetailEntity to UserDetailDto conversion`() {
        val userDetailEntity = UserDetailEntity("user1", "url1", "html1", "location", 100, 200)
        val userDetailDto = userDetailEntity.toDto()

        assertEquals("user1", userDetailDto.login)
        assertEquals("url1", userDetailDto.avatar_url)
        assertEquals("html1", userDetailDto.html_url)
        assertEquals("location", userDetailDto.location)
        assertEquals(100, userDetailDto.followers)
        assertEquals(200, userDetailDto.following)
    }
}