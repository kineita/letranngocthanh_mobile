package com.letranngocthanh.mobile.feature.users

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.letranngocthanh.mobile.R
import com.letranngocthanh.mobile.navigator.NavigationRoutes
import com.letranngocthanh.mobile.ui.common.OnBottomReached
import com.letranngocthanh.mobile.ui.common.OnePixelLine
import com.letranngocthanh.mobile.ui.common.TopAppBar
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.feature.users.UiEvent
import com.letranngocthanh.presentation.feature.users.UserListViewModel
import com.letranngocthanh.presentation.model.users.UserUI
import org.koin.compose.koinInject

@Composable
fun UserListScreen(navController: NavHostController) {
    val viewModel: UserListViewModel = koinInject()
    val viewState by viewModel.viewState
    val uiEvent by viewModel.uiEvent
    val context = LocalContext.current

    // Observe UI events and show Toast
    LaunchedEffect(uiEvent) {
        if (uiEvent is UiEvent.ShowToast) {
            Toast.makeText(context, (uiEvent as UiEvent.ShowToast).message, Toast.LENGTH_SHORT).show()
        }
    }

    val listState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Add TopAppBar with a back button or title as needed
        Column {
            TopAppBar(
                title = stringResource(R.string.toolbar_title_users),
                onBackButtonClick = {
                    navController.navigateUp()
                }
            )

            when (viewState) {
                is ViewState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is ViewState.Error -> {
                    Text(
                        text = "Error: ${(viewState as ViewState.Error).t.message}",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is ViewState.Success -> {
                    val users = (viewState as ViewState.Success<List<UserUI>>).data
                    UserListContent(
                        users = users,
                        userListViewModel = viewModel,
                        navController = navController,
                        listState = listState
                    )
                }
            }
        }
    }
}

@Composable
fun UserListContent(
    users: List<UserUI>,
    userListViewModel: UserListViewModel,
    navController: NavHostController,
    listState: LazyListState
) {
    val loadingMore by userListViewModel.loadingMore

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(users) { user ->
                UserListItem(user = user) {
                    navController.navigate(NavigationRoutes.userDetailRoute(userId = user.login))
                }
            }

            if (loadingMore) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        if (loadingMore) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
            )
        }

        listState.OnBottomReached(buffer = 3) {
            userListViewModel.onBottomReached()
        }
    }
}

@Composable
fun UserListItem(user: UserUI, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp) // Set elevation using CardDefaults
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val painter = rememberAsyncImagePainter(user.avatar_url)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 1.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                OnePixelLine()

                Text(
                    text = user.html_url,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = 1.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserListItem() {
    Surface {
        UserListItem(
            user = UserUI(
                login = "David",
                avatar_url = "http://example.com/avatar.png",
                html_url = "https://www.linkedin.com/"
            ),
            onClick = {}
        )
    }
}