package com.example.littlelemon.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

sealed class NavEvent() {
    object NavigateToOnboarding : NavEvent()
    object NavigateToHome : NavEvent()
    object NavigateToProfile : NavEvent()
    object NavigateToDishDetails : NavEvent()
}

fun navigateTo(navEvent: NavEvent, navController: NavHostController) {
    when (navEvent) {
        NavEvent.NavigateToOnboarding -> navController.navigate(Destinations.Onboarding.route) {
            setupNavigation(navController)
        }

        is NavEvent.NavigateToHome -> navController.navigate(Destinations.Home.route) {
            setupNavigation(navController)
        }
        is NavEvent.NavigateToProfile -> navController.navigate(Destinations.Profile.route) {
            setupNavigation(navController)
        }
        is NavEvent.NavigateToDishDetails -> navController.navigate(Destinations.DishDetails.route) {
            setupNavigation(navController)
        }
    }
}

fun NavOptionsBuilder.setupNavigation(navController: NavController) {
    navController.graph.startDestinationRoute?.let { route ->
        popUpTo(route) {
            saveState = true
        }
    }
    launchSingleTop = true
    restoreState = true
}