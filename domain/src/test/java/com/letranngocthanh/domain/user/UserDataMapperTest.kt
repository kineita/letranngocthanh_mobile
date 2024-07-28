package com.letranngocthanh.domain.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.domain.feature.user.DefaultUserDataMapper
import com.letranngocthanh.domain.feature.user.UserDataMapper
import com.letranngocthanh.domain.model.user.User
import com.letranngocthanh.domain.model.user.UserDetail
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
class UserDataMapperTest {

    private val userDataMapper: UserDataMapper = DefaultUserDataMapper()

    @Test
    fun `toUserDetail should map UserDetailDto to UserDetail`() {
        val userDetailDto = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        val userDetail = userDataMapper.toUserDetail(userDetailDto)

        Assertions.assertEquals(UserDetail("user1", "url1", "html1", "location", 100, 200), userDetail)
    }

    @Test
    fun `toUsers should map list of UserDto to list of User`() {
        val usersDto = listOf(UserDto("user1", "url1", "html1"), UserDto("user2", "url2", "html2"))
        val users = userDataMapper.toUsers(usersDto)

        Assertions.assertEquals(listOf(User("user1", "url1", "html1"), User("user2", "url2", "html2")), users)
    }
}