package com.msc.application.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.msc.application.presentation.splashActivity.PhotoViewModel
import com.msc.application.presentation.news_navigator.NewsNavigator
import com.msc.application.presentation.onboarding.OnBoardingScreen
import com.msc.application.presentation.onboarding.OnBoardingViewModel

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
            composable(route = Route.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }

//        navigation(
//            route = Route.NewsNavigation.route,
//            startDestination = Route.NewsNavigatorScreen.route
//        ) {
//            composable(
//                route = Route.NewsNavigatorScreen.route
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
////                    val viewModel: SearchViewModel = hiltViewModel()
////                    SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
//                    val viewModel: BookmarkViewModel = hiltViewModel()
//                    BookmarkScreen(state = viewModel.state.value, navigate = {})
//                }
//            }
//        }
    }
    
}