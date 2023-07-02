package com.arturlasok.createitnow.android

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun OneAppView(
    modifier: Modifier,
    imgUrl: String,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(true)
            .build(),

        contentDescription = "cd",
        contentScale = ContentScale.Fit,
        modifier = modifier.padding(start = 16.dp, end = 16.dp)

    )
}