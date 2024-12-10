package com.example.littlelemon.presentation.viewmodel

import com.example.littlelemon.data.model.User

sealed class HomeEvent {
    class Search(val query: String) : HomeEvent()
    class Filter(val category: String) : HomeEvent()
    class Login(val user: User) : HomeEvent()
    object Logout : HomeEvent()
    object GetAllMenuItems : HomeEvent()
    object GetUser : HomeEvent()

}