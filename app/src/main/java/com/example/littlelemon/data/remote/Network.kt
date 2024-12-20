package com.example.littlelemon.data.remote

import com.example.littlelemon.data.local.MenuItemRoom
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
){
    fun toLocal() = MenuItemRoom(
        image = image,
        price = price.toDouble(),
        description = description,
        id = id,
        title = title,
        category = category
    )
}
