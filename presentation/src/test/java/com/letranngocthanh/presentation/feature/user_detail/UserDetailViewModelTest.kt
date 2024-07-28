package com.letranngocthanh.presentation.feature.user_detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.base.Verify.verify
import com.letranngocthanh.domain.feature.user.GetUserDetailUseCase
import com.letranngocthanh.domain.model.user.UserDetail
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.model.users.UserDetailUI
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getUserDetailUseCase: GetUserDetailUseCase
    private lateinit var userDetailMapper: UserDetailMapper
    private lateinit var viewModel: UserDetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        getUserDetailUseCase = mockk()
        userDetailMapper = mockk()
        Dispatchers.setMain(testDispatcher) // Set Main dispatcher to test dispatcher
        viewModel = UserDetailViewModel(getUserDetailUseCase, userDetailMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset Main dispatcher to the original Main dispatcher
    }

    @Test
    fun `fetchUserDetail - success`() = runTest {
        val userDetail = UserDetail(
            login = "login",
            avatar_url = "avatar_url",
            html_url = "html_url",
            location = "location",
            followers = 100,
            following = 50
        )
        val userDetailUI = UserDetailUI(
            login = "login",
            avatar_url = "avatar_url",
            html_url = "html_url",
            location = "location",
            followers = 100,
            following = 50
        )
        val useCaseResult = Result.success(userDetail)

        coEvery { getUserDetailUseCase(any()) } returns useCaseResult
        every { userDetailMapper.toUserDetailUI(userDetail) } returns userDetailUI

        viewModel.fetchUserDetail("userId")

        advanceUntilIdle()

        assertEquals(ViewState.Success(userDetailUI), viewModel.viewState.value)
        coVerify(exactly = 1) { getUserDetailUseCase(any()) }
        verify(exactly = 1) { userDetailMapper.toUserDetailUI(userDetail) }
    }

    @Test
    fun `fetchUserDetail - failure`() = runTest {
        val exception = Exception("Error fetching user detail")
        val useCaseResult = Result.failure<UserDetail>(exception)

        coEvery { getUserDetailUseCase(any()) } returns useCaseResult

        viewModel.fetchUserDetail("userId")

        advanceUntilIdle()

        assertEquals(ViewState.Error(exception), viewModel.viewState.value)
        coVerify(exactly = 1) { getUserDetailUseCase(any()) }
        verify(exactly = 0) { userDetailMapper.toUserDetailUI(any()) }
    }
}