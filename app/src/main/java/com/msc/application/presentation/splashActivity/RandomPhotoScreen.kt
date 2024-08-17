package com.msc.application.presentation.splashActivity

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RandomPhotoScreen(
    viewModel: PhotoViewModel
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(model = viewModel.photos.value.get(0), contentDescription = null)
    }

}