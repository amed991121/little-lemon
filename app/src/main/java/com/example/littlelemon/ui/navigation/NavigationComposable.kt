package com.example.littlelemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.data.repository.PreferenceRepository
import com.example.littlelemon.presentation.viewmodel.HomeEvent
import com.example.littlelemon.presentation.viewmodel.HomeState
import com.example.littlelemon.ui.screen.DishDetails
import com.example.littlelemon.ui.screen.HomeScreen
import com.example.littlelemon.ui.screen.Onboarding
import com.example.littlelemon.ui.screen.Profile

@Composable
fun Navigation(
    navController: NavHostController,
    preferenceRepository: PreferenceRepository,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = if (preferenceRepository.isUserLoggedIn())
            Destinations.Home.route
        else
            Destinations.Onboarding.route
    ) {
        composable(Destinations.Onboarding.route) {
            Onboarding(
                navController,
                onEvent
            )
        }
        composable(Destinations.Home.route) {
            HomeScreen(
                menuItems = state.menuItems,
                menuItemsFiltered = state.menuItemsFiltered,
                navController = navController,
                onEvent = onEvent,
                navEvent = { navEvent ->
                    navigateTo(
                        navEvent = navEvent,
                        navController = navController
                    )
                }
            )
        }
        composable(Destinations.Profile.route) {
            Profile(
                state.user,
                navController,
                onEvent
            )
        }
        composable(Destinations.DishDetails.route) {
            DishDetails(
                dish = state.dish,
                navEvent = { navEvent ->
                    navigateTo(
                        navEvent = navEvent,
                        navController = navController
                    )
                }
            )
        }
    }
}