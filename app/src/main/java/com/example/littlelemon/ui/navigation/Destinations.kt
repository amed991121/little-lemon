package com.example.littlelemon.ui.navigation

interface Destinations {
    val route: String

    object Onboarding : Destinations {
        override val route = "onboarding"
    }
    object Home : Destinations {
        override val route = "home"
    }
    object Profile : Destinations {
        override val route = "profile"
    }
    object DishDetails : Destinations {
        override val route = "menu"
    }
}