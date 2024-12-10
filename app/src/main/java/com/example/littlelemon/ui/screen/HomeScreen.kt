package com.example.littlelemon.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.littlelemon.data.local.MenuItemRoom
import com.example.littlelemon.presentation.viewmodel.HomeEvent
import com.example.littlelemon.ui.component.LowerPanel
import com.example.littlelemon.ui.component.TopAppBar
import com.example.littlelemon.ui.component.UpperPanel
import com.example.littlelemon.ui.navigation.NavEvent

@Composable
fun HomeScreen(
    menuItems: List<MenuItemRoom>,
    menuItemsFiltered: List<MenuItemRoom>,
    navController: NavHostController,
    onEvent: (HomeEvent) -> Unit,
    navEvent: (NavEvent) -> Unit,
) {
    Column {
        TopAppBar(navEvent = navEvent)
        UpperPanel(onSearch = { onEvent(HomeEvent.Search(it)) } )
        LowerPanel(navController, menuItems, menuItemsFiltered, onEvent)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        menuItems = listOf(),
        navController = NavHostController(LocalContext.current),
        onEvent = {},
        navEvent = {},
        menuItemsFiltered = listOf()
    )
}
