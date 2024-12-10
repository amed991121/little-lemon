package com.example.littlelemon.presentation.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.Message
import com.example.littlelemon.Message.*
import com.example.littlelemon.data.local.MenuItemDao
import com.example.littlelemon.data.model.User
import com.example.littlelemon.data.repository.AppRepository
import com.example.littlelemon.data.repository.Dish
import com.example.littlelemon.data.repository.PreferenceRepository
import com.example.littlelemon.presentation.viewmodel.HomeViewModel.OutputEvent.*
import com.example.littlelemon.ui.navigation.Destinations
import io.ktor.http.ContentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val menuItemDao: MenuItemDao,
    private val preferenceRepository: PreferenceRepository,
    private val appRepository: AppRepository
) : ViewModel() {

    open class OutputEvent {
        class ShowMessage(val message: Message) : OutputEvent()
        class Logged(val user: User) : OutputEvent()
    }

    protected val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    protected val _outputEvent = MutableSharedFlow<OutputEvent>()
    val outputEvent = _outputEvent.asSharedFlow()

    init {
        fetchMenuItems()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Filter -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val items = menuItemDao.getAll()
                    val filteredItems = items.filter { menuItem ->
                        if (event.category == "All") {
                            return@filter true
                        } else {
                            menuItem.category.contains(event.category)
                        }
                    }
                    _state.update { state ->
                        state.copy(menuItemsFiltered = filteredItems)
                    }
                }
            }

            is HomeEvent.GetAllMenuItems -> {}
            is HomeEvent.Search -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val items = menuItemDao.getAll()
                    val filteredItems = items.filter { menuItem ->
                        menuItem.title.lowercase().contains(event.query.lowercase())
                    }
                    _state.update { state ->
                        state.copy(menuItemsFiltered = filteredItems)
                    }
                }
            }

            is HomeEvent.Login -> {
                login(event.user)
            }

            is HomeEvent.Logout -> {
                viewModelScope.launch {
                    preferenceRepository.clearUser()
                }
            }

            is HomeEvent.GetUser -> {
                viewModelScope.launch {
                    preferenceRepository.getUser().collectLatest {
                        _state.update { state ->
                            state.copy(user = it)
                        }
                    }
                    Log.d("user", state.value.user.toString())
                }
            }

            is HomeEvent.GetDish -> {
                viewModelScope.launch {
                    val menuItems = menuItemDao.getAll()
                    val dish = menuItems.find { it.id == event.id }
                    _state.update { state ->
                        state.copy(dish = Dish(
                            id = dish!!.id,
                            name = dish.title,
                            description = dish.description,
                            price = dish.price,
                            imageResource = dish.image
                        ))
                    }
                }
            }
        }
    }


    private fun fetchMenuItems() {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.getMenuData().collectLatest {
                _state.update { state ->
                    state.copy(menuItems = it, menuItemsFiltered = it)
                }
            }
        }
    }

    private fun login(user: User) {
        if (
            user.firstName.isNotBlank() &&
            user.lastName.isNotBlank() &&
            user.email.isNotBlank()
        ) {
            viewModelScope.launch {
                val isUserSaved = preferenceRepository.saveUser(user)
                withContext(Dispatchers.Main) {
                    if (isUserSaved) {
                        _outputEvent.emit(ShowMessage(DynamicString("Registration successful!")))
                        _outputEvent.emit(Logged(user))

                    } else
                        _outputEvent.emit(ShowMessage(DynamicString("Registration unsuccessful. Please enter all data.")))
                }
            }
        } else {
            viewModelScope.launch {
                _outputEvent.emit(ShowMessage(DynamicString("Registration unsuccessful. Please enter all data.")))
            }
        }
    }
}
