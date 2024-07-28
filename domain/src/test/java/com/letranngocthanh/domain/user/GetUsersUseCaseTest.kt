package com.letranngocthanh.domain.user

import com.letranngocthanh.data.model.UserDto
import com.letranngocthanh.data.repo.user.UserRepository
import com.letranngocthanh.domain.exception.pagination.InvalidPageNumberException
import com.letranngocthanh.domain.exception.pagination.InvalidPageSizeException
import com.letranngocthanh.domain.feature.user.DefaultUserDataMapper
import com.letranngocthanh.domain.feature.user.GetUsersUseCase
import com.letranngocthanh.domain.feature.user.UserDataMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private val userRepository: UserRepository = mockk()
    private val userDataMapper: UserDataMapper = DefaultUserDataMapper()
    private val getUsersUseCase = GetUsersUseCase(userRepository, userDataMapper)

    @Test
    fun `execute should return list of users`() = runTest {
        val usersDto = listOf(UserDto("user1", "url1", "html1"), UserDto("user2", "url2", "html2"))
        coEvery { userRepository.getUsers(20, 0) } returns Result.success(usersDto)

        val params = GetUsersUseCase.Params(perPage = 20, since = 0)
        val result = getUsersUseCase(params)

        assertEquals(Result.success(userDataMapper.toUsers(usersDto)), result)
        coVerify { userRepository.getUsers(20, 0) }
    }

    @Test
    fun `invoke should throw InvalidPageNumberException when since is negative`() = runTest {
        val params = GetUsersUseCase.Params(perPage = 20, since = -1)

        assertFailsWith<InvalidPageNumberException> {
            getUsersUseCase(params)
        }
    }

    @Test
    fun `invoke should throw InvalidPageSizeException when perPage is zero or negative`() = runTest {
        val paramsZero = GetUsersUseCase.Params(perPage = 0, since = 0)
        val paramsNegative = GetUsersUseCase.Params(perPage = -1, since = 0)

        assertFailsWith<InvalidPageSizeException> {
            getUsersUseCase(paramsZero)
        }
        assertFailsWith<InvalidPageSizeException> {
            getUsersUseCase(paramsNegative)
        }
    }
}