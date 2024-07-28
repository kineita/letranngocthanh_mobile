package com.letranngocthanh.data.repo.user

import com.letranngocthanh.data.exception.user.CannotFetchUserDetailInLocalAndRemoteDataSource
import com.letranngocthanh.data.exception.user.CannotFetchUsersInLocalAndRemoteDataSource
import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.source.user.local.UserLocalDataSource
import com.letranngocthanh.data.source.user.remote.UserRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.Test
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

@ExperimentalCoroutinesApi
class DefaultUserRepositoryTest {

    private val remoteDataSource: UserRemoteDataSource = mockk()
    private val localDataSource: UserLocalDataSource = mockk()
    private val repository = DefaultUserRepository(remoteDataSource, localDataSource)

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUsers should return users from remote and save them to local`() = runTest {
        val users = listOf(UserDto("user1", "url1", "html1"), UserDto("user2", "url2", "html2"))
        coEvery { remoteDataSource.getUsers(20, 0) } returns users
        coEvery { localDataSource.saveUsers(users) } returns Unit

        val result = repository.getUsers(20, 0)

        assertTrue(result.isSuccess)
        assertEquals(users, result.getOrNull())
        coVerify { localDataSource.saveUsers(users) }
    }

    @Test
    fun `getUsers should return users from local when remote fails`() = runTest {
        val localUsers = listOf(UserDto("user1", "url1", "html1"))
        coEvery { remoteDataSource.getUsers(20, 0) } throws Exception("Remote error")
        coEvery { localDataSource.getUsers(20, 0) } returns localUsers

        val result = repository.getUsers(20, 0)

        assertTrue(result.isSuccess)
        assertEquals(localUsers, result.getOrNull())
    }

    @Test
    fun `getUsers should throw exception when both remote and local fail`() = runTest {
        coEvery { remoteDataSource.getUsers(20, 0) } throws Exception("Remote error")
        coEvery { localDataSource.getUsers(20, 0) } returns emptyList()

        val result = repository.getUsers(20, 0)

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is CannotFetchUsersInLocalAndRemoteDataSource)
    }

    @Test
    fun `getUserDetail should return user detail from remote and save it to local`() = runTest {
        val userDetail = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        coEvery { remoteDataSource.getUserDetail("user1") } returns userDetail
        coEvery { localDataSource.saveUserDetail(userDetail) } returns Unit

        val result = repository.getUserDetail("user1")

        assertTrue(result.isSuccess)
        assertEquals(userDetail, result.getOrNull())
        coVerify { localDataSource.saveUserDetail(userDetail) }
    }

    @Test
    fun `getUserDetail should return user detail from local when remote fails`() = runTest {
        val localUserDetail = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        coEvery { remoteDataSource.getUserDetail("user1") } throws Exception("Remote error")
        coEvery { localDataSource.getUserDetail("user1") } returns localUserDetail

        val result = repository.getUserDetail("user1")

        assertTrue(result.isSuccess)
        assertEquals(localUserDetail, result.getOrNull())
    }

    @Test
    fun `getUserDetail should throw exception when both remote and local fail`() = runTest {
        coEvery { remoteDataSource.getUserDetail("user1") } throws Exception("Remote error")
        coEvery { localDataSource.getUserDetail("user1") } returns null

        val result = repository.getUserDetail("user1")

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is CannotFetchUserDetailInLocalAndRemoteDataSource)
    }
}