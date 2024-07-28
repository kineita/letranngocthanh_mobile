package com.letranngocthanh.presentation.feature.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.letranngocthanh.domain.feature.user.GetUsersUseCase
import com.letranngocthanh.domain.model.user.User
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.model.users.UserUI
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val userUIMapper: UserUIMapper = mockk()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = UserListViewModel(getUsersUseCase, userUIMapper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchUsers should update viewState to Success when useCase returns success`() {
        // Arrange
        val userList = listOf(User(login = "login1", avatar_url = "url1", html_url = "html1"))
        val userUIList = listOf(UserUI(login = "login1", avatar_url = "url1", html_url = "html1"))
        coEvery { getUsersUseCase(any()) } returns Result.success(userList)
        coEvery { userUIMapper.toUserUIs(userList) } returns userUIList

        // Act
        viewModel.fetchUsers()

        // Assert
        assertEquals(ViewState.Success(userUIList), viewModel.viewState.value)
        coVerify { getUsersUseCase(any()) }
        coVerify { userUIMapper.toUserUIs(userList) }
    }

    @Test
    fun `fetchUsers should update viewState to Error when useCase returns failure`() {
        // Arrange
        val exception = Exception("Error fetching users")
        coEvery { getUsersUseCase(any()) } returns Result.failure(exception)

        // Act
        viewModel.fetchUsers()

        // Assert
        assertEquals(ViewState.Error(exception), viewModel.viewState.value)
        coVerify { getUsersUseCase(any()) }
    }

    @Test
    fun `onBottomReached should load more users and update viewState to Success`() {
        // Arrange
        val userList = listOf(User(login = "login1", avatar_url = "url1", html_url = "html1"))
        val userUIList = listOf(UserUI(login = "login1", avatar_url = "url1", html_url = "html1"))
        coEvery { getUsersUseCase(any()) } returns Result.success(userList)
        coEvery { userUIMapper.toUserUIs(userList) } returns userUIList

        // Act
        viewModel.onBottomReached()

        // Assert
        assertEquals(ViewState.Success(userUIList), viewModel.viewState.value)
        assertEquals(false, viewModel.loadingMore.value)
        coVerify { getUsersUseCase(any()) }
        coVerify { userUIMapper.toUserUIs(userList) }
    }

    @Test
    fun `onBottomReached should update uiEvent to ShowToast when useCase returns failure`() {
        // Arrange
        val exception = Exception("Error loading more users")
        coEvery { getUsersUseCase(any()) } returns Result.failure(exception)

        // Act
        viewModel.onBottomReached()

        // Assert
        assertEquals(UiEvent.ShowToast("Failed to load more users: ${exception.message}"), viewModel.uiEvent.value)
        assertEquals(false, viewModel.loadingMore.value)
        coVerify { getUsersUseCase(any()) }
    }
}