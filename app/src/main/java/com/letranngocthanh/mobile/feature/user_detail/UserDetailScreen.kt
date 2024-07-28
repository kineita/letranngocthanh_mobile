package com.letranngocthanh.mobile.feature.user_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.letranngocthanh.mobile.R
import com.letranngocthanh.mobile.ui.common.OnePixelLine
import com.letranngocthanh.mobile.ui.common.TopAppBar
import com.letranngocthanh.presentation.ViewState
import com.letranngocthanh.presentation.feature.user_detail.UserDetailViewModel
import com.letranngocthanh.presentation.model.users.UserDetailUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetailScreen(userId: String, navController: NavHostController) {
    val viewModel: UserDetailViewModel = koinViewModel()
    val viewState by viewModel.viewState

    LaunchedEffect(userId) {
        viewModel.fetchUserDetail(userId = userId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Add TopAppBar with a back button or title as needed
        Column {
            TopAppBar(
                title = stringResource(R.string.toolbar_title_user_details),
                showBackIcon = true,
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
                    val userDetail = (viewState as ViewState.Success<UserDetailUI>).data
                    UserDetailView(viewModel = viewModel, userDetailUI = userDetail)
                }
            }
        }
    }
}

@Composable
fun UserDetailView(viewModel: UserDetailViewModel, userDetailUI: UserDetailUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = {

            }),
        elevation = CardDefaults.cardElevation(4.dp) // Set elevation using CardDefaults
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val painter = rememberAsyncImagePainter(userDetailUI.avatar_url)

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
                        text = userDetailUI.login,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 1.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OnePixelLine()

                    Text(
                        text = userDetailUI.html_url,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 1.sp
                        ),
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            FooterUserDetail(userDetailUI = userDetailUI)
        }
    }
}

@Composable
fun FooterUserDetail(userDetailUI: UserDetailUI) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Follower")
            Image(
                painter = painterResource(id = R.drawable.ic_follower),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Text(text = "${userDetailUI.followers}")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Following")
            Image(
                painter = painterResource(id = R.drawable.ic_following),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Text(text = "${userDetailUI.following}")
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(text = "Blog")
    Text(
        text = userDetailUI.html_url,
        style = MaterialTheme.typography.bodyMedium.copy(
            lineHeight = 1.sp
        ),
        color = MaterialTheme.colorScheme.primary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillMaxWidth()
    )
}