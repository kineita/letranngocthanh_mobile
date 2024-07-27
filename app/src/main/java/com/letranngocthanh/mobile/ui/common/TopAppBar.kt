package com.letranngocthanh.mobile.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.letranngocthanh.mobile.R

@Composable
fun TopAppBar(
    title: String,
    showBackIcon: Boolean = false,
    onBackButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp), // Adjust padding as needed
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (showBackIcon) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        if (showBackIcon) {
            IconButton(onClick = { onBackButtonClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.txt_back)
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(horizontal = if (showBackIcon) 48.dp else 0.dp) // Adjust padding to account for back icon
        )
    }
}