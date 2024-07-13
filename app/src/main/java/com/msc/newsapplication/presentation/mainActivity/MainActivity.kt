package com.msc.newsapplication.presentation.mainActivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.msc.newsapplication.domain.usecases.AppEntryUseCases
import com.msc.newsapplication.presentation.PhotoViewModel
import com.msc.newsapplication.presentation.onboarding.OnBoardingEvent
import com.msc.newsapplication.presentation.onboarding.OnBoardingScreen
import com.msc.newsapplication.presentation.onboarding.OnBoardingViewModel
import com.msc.newsapplication.ui.theme.NewsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    
    private val photoViewModel: PhotoViewModel by viewModels()

    @Inject
    lateinit var appEntryUseCases: AppEntryUseCases
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { false } )
        }

        lifecycleScope.launch {
            appEntryUseCases.readAppEntry().collect {
                Log.d("TAG", "onCreate: readAppEntry > ${it.toString()}")
            }
        }

        setContent {
            NewsApplicationTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(photoViewModel, event = viewModel::onEvent)
                }
            }
        }
    }
}
