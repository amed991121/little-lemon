package com.example.littlelemon.presentation.viewmodel

import com.example.littlelemon.data.local.MenuItemRoom
import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.repository.Dish

data class HomeState (
    val menuItems: List<MenuItemRoom> = emptyList(),
    val menuItemsFiltered: List<MenuItemRoom> = emptyList(),
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)