package com.example.lazybone.main.ui.components.exerciseDetails

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun WorkoutGif(gifUrl: String) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components { add(ImageDecoderDecoder.Factory()) }
        .build()
    Column {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(gifUrl)
                .crossfade(true) // Smooth transition
                .build(),
            imageLoader = imageLoader,
            contentDescription = "Exercise Animation",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
    }
}