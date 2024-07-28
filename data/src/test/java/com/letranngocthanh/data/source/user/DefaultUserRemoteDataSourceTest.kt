package com.letranngocthanh.data.source.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.source.user.remote.DefaultUserRemoteDataSource
import com.letranngocthanh.data.source.user.remote.UserService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultUserRemoteDataSourceTest {

    private val userService: UserService = mockk()
    private val remoteDataSource = DefaultUserRemoteDataSource(userService)

    @Test
    fun `getUsers should return users from UserService`() = runTest {
        val users = listOf(UserDto("user1", "url1", "html1"), UserDto("user2", "url2", "html2"))
        coEvery { userService.getUsers(20, 0) } returns users

        val result = remoteDataSource.getUsers(20, 0)

        assertEquals(users, result)
        coVerify { userService.getUsers(20, 0) }
    }

    @Test
    fun `getUserDetail should return user detail from UserService`() = runTest {
        val userDetail = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        coEvery { userService.getUserDetail("user1") } returns userDetail

        val result = remoteDataSource.getUserDetail("user1")

        assertEquals(userDetail, result)
        coVerify { userService.getUserDetail("user1") }
    }
}