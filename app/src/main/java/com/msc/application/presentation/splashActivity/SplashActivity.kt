package com.msc.application.presentation.splashActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.msc.application.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
//@AndroidEntryPoint
class SplashActivity: ComponentActivity() {

    private val photoViewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        photoViewModel.fetchPhotos()

//        setContent {
//            NewsApplicationTheme {
//                Surface(
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    RandomPhotoScreen(photoViewModel)
//                }
//
//            }
//        }

    }

}