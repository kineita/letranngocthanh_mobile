package com.letranngocthanh.data.source.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDetailEntity
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.model.UserEntity
import com.letranngocthanh.data.source.user.local.DefaultUserLocalDataSource
import com.letranngocthanh.data.source.user.local.UserDao
import com.letranngocthanh.data.source.user.local.toDto
import com.letranngocthanh.data.source.user.local.toEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultUserLocalDataSourceTest {

    private val userDao: UserDao = mockk()
    private val localDataSource = DefaultUserLocalDataSource(userDao)

    @Test
    fun `getUsers should return users from UserDao`() = runTest {
        val userEntities = listOf(
            UserEntity("user1", "url1", "html1", 0),
            UserEntity("user2", "url2", "html2", 1)
        )
        coEvery { userDao.getUsersPaginated(20, 0) } returns userEntities

        val expectedUsers = userEntities.map { it.toDto() }
        val result = localDataSource.getUsers(20, 0)

        assertEquals(expectedUsers, result)
        coVerify { userDao.getUsersPaginated(20, 0) }
    }

    @Test
    fun `saveUsers should insert users into UserDao`() = runTest {
        val users = listOf(UserDto("user1", "url1", "html1"), UserDto("user2", "url2", "html2"))
        val userEntities = users.mapIndexed { index, userDto -> userDto.toEntity(index) }
        coEvery { userDao.insertUsers(userEntities) } returns Unit

        localDataSource.saveUsers(users)

        coVerify { userDao.insertUsers(userEntities) }
    }

    @Test
    fun `getUserDetail should return user detail from UserDao`() = runTest {
        val userDetailEntity = UserDetailEntity("user1", "url1", "html1", "location", 100, 200)
        coEvery { userDao.getUserDetail("user1") } returns userDetailEntity

        val expectedUserDetail = userDetailEntity.toDto()
        val result = localDataSource.getUserDetail("user1")

        assertEquals(expectedUserDetail, result)
        coVerify { userDao.getUserDetail("user1") }
    }

    @Test
    fun `saveUserDetail should insert user detail into UserDao`() = runTest {
        val userDetail = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        val userDetailEntity = userDetail.toEntity()
        coEvery { userDao.insertUserDetail(userDetailEntity) } returns Unit

        localDataSource.saveUserDetail(userDetail)

        coVerify { userDao.insertUserDetail(userDetailEntity) }
    }
}