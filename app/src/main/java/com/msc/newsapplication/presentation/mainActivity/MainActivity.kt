package com.msc.newsapplication.presentation.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.msc.newsapplication.presentation.PhotoViewModel
import com.msc.newsapplication.presentation.onboarding.OnBoardingScreen
import com.msc.newsapplication.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val photoViewModel: PhotoViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { false } )
        }

        setContent {
            NewsApplicationTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnBoardingScreen(photoViewModel)
                }
            }
        }
    }
}
