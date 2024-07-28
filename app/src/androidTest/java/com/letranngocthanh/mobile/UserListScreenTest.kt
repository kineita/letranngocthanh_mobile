package com.letranngocthanh.mobile

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import com.letranngocthanh.mobile.feature.users.UserListScreen
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.feature.users.UiEvent
import com.letranngocthanh.presentation.feature.users.UserListViewModel
import com.letranngocthanh.presentation.model.users.UserUI
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class UserListScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel: UserListViewModel by inject()
    private val navController = mockk<NavHostController>(relaxed = true)

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    single {
                        mockk<UserListViewModel>(relaxed = true).apply {
                            every { viewState } returns mutableStateOf(
                                ViewState.Success(
                                    listOf(UserUI("David", "http://example.com/avatar.png", "https://www.linkedin.com/"))
                                )
                            )
                            every { uiEvent } returns mutableStateOf(UiEvent.None)
                            coEvery { fetchUsers() } returns Unit
                        }
                    }
                }
            )
        }
    }

    @After
    fun teardown() {
        stopKoin()
    }

    @Test
    fun userListScreen_displaysUsers() {
        composeTestRule.setContent {
            UserListScreen(navController)
        }

        composeTestRule.onNodeWithText("David").assertIsDisplayed()
    }

    @Test
    fun userListScreen_navigateToUserDetailOnClick() {
        composeTestRule.setContent {
            UserListScreen(navController)
        }

        composeTestRule.onNodeWithText("David").performClick()
    }
}