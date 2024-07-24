package com.msc.newsapplication.presentation.navgraph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.msc.newsapplication.presentation.PhotoViewModel
import com.msc.newsapplication.presentation.home.HomeScreen
import com.msc.newsapplication.presentation.home.HomeViewModel
import com.msc.newsapplication.presentation.onboarding.OnBoardingScreen
import com.msc.newsapplication.presentation.onboarding.OnBoardingViewModel
import com.msc.newsapplication.presentation.search.SearchScreen
import com.msc.newsapplication.presentation.search.SearchViewModel

@Composable
fun NavGraph (
    startDestination: String
) {
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route, // 시작 위치
            startDestination = Route.OnBoardingScreen.route // 최종 위치
        ) {
            composable(
                route = Route.OnBoardingScreen.route // 최종 위치 경로에 따른 composable 호출
            ) {
                val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
                val photoViewModel: PhotoViewModel = hiltViewModel()
                OnBoardingScreen(photoViewModel, event = onBoardingViewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val viewModel: SearchViewModel = hiltViewModel()
                    SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})

                }
            }
        }
    }
    
}