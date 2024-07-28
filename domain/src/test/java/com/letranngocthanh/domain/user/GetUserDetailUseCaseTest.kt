package com.letranngocthanh.domain.user

import com.letranngocthanh.data.model.UserDetailDto
import com.letranngocthanh.data.repo.user.UserRepository
import com.letranngocthanh.domain.exception.user.InvalidUserIdException
import com.letranngocthanh.domain.feature.user.DefaultUserDataMapper
import com.letranngocthanh.domain.feature.user.GetUserDetailUseCase
import com.letranngocthanh.domain.feature.user.UserDataMapper
import com.letranngocthanh.domain.model.user.UserDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetUserDetailUseCaseTest {

    private val userRepository: UserRepository = mockk()
    private val userDataMapper = DefaultUserDataMapper()
    private val getUserDetailUseCase = GetUserDetailUseCase(userRepository, userDataMapper)

    @Test
    fun `execute should return user detail`() = runTest {
        val userDetailDto = UserDetailDto("user1", "url1", "html1", "location", 100, 200)
        coEvery { userRepository.getUserDetail("user1") } returns Result.success(userDetailDto)

        val params = GetUserDetailUseCase.Params("user1")
        val result: Result<UserDetail> = getUserDetailUseCase(params)

        assertEquals(Result.success(userDataMapper.toUserDetail(userDetailDto)), result)
        coVerify { userRepository.getUserDetail("user1") }
    }

    @Test
    fun `invoke should throw InvalidUserIdException when userId is empty`() = runTest {
        val params = GetUserDetailUseCase.Params("")

        assertFailsWith<InvalidUserIdException> {
            getUserDetailUseCase(params)
        }
    }

    @Test
    fun `execute should handle error from repository`() = runTest {
        val exception = Exception("Network Error")
        coEvery { userRepository.getUserDetail("user1") } returns Result.failure(exception)

        val params = GetUserDetailUseCase.Params("user1")
        val result: Result<UserDetail> = getUserDetailUseCase(params)

        assertEquals(Result.failure<UserDetail>(exception), result)
        coVerify { userRepository.getUserDetail("user1") }
    }
}