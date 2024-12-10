package com.example.littlelemon.data.repository

import android.content.Context
import com.example.littlelemon.data.local.AppDatabase
import com.example.littlelemon.data.remote.LittleLemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository (
    private val littleLemonApi: LittleLemonApi,
    private val appDatabase: AppDatabase
) {

    fun getMenuData() = flow {
        //get the items from the local
        var localItems = appDatabase.getMenuItemDao().getAll()

        //if there are no items in the local db
        //then fetch from the network, cache it
        //and then return the cached data
        if (localItems.isEmpty()) {
            val networkItems = littleLemonApi.getMenuData()
            localItems = networkItems.menu.map { it.toLocal() }
            appDatabase.getMenuItemDao().insertAll(localItems)
        }
        emit(localItems)
    }.flowOn(Dispatchers.IO)
}