package com.example.littlelemon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.navigation.Destinations
import com.example.littlelemon.data.repository.Dish
import com.example.littlelemon.R
import com.example.littlelemon.data.local.MenuItemRoom
import com.example.littlelemon.presentation.viewmodel.HomeEvent
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun LowerPanel(
    navController: NavHostController,
    menuItems: List<MenuItemRoom>,
    menuItemsFiltered: List<MenuItemRoom>,
    onEvent: (HomeEvent) -> Unit,
) {
    Column {
        MenuBreakDown(
            menuItems = menuItems,
            selectedCategory = null,
            onCategorySelectionChange = { onEvent(HomeEvent.Filter(it.toString())) }
        )
        LazyColumn {
            itemsIndexed(menuItemsFiltered) { _, item ->
                MenuDish(
                    navController, Dish(
                        id = item.id,
                        name = item.title,
                        description = item.description,
                        price = item.price,
                        imageResource = item.image
                    )
                )
            }
        }
    }
}

@Composable
fun CategoryItem(
    categoryName: String,
    selectedCategory: String?,
    onCheckedChange: (category: String?) -> Unit
) {
    Text(
        text = categoryName,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2F) ,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp)
            .clickable {
                onCheckedChange(
                    if (selectedCategory == categoryName)
                        "All"
                    else categoryName
                )
            },
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(onClick = {
        //navController?.navigate(Destinations.DishDetails.route + "/${dish.id}")
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
            ) {
                Text(
                    text = dish.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp),
                    text = dish.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${dish.price}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            GlideImage(
                model = dish.imageResource,
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
    )
}

@Composable
fun MenuBreakDown(
    menuItems: List<MenuItemRoom>,
    selectedCategory: String?,
    onCategorySelectionChange: (selectedCategory: String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_menu_breakdown_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            itemsIndexed(menuItems) { _, item ->
                CategoryItem(
                    categoryName = item.category,
                    selectedCategory = selectedCategory,
                    onCheckedChange = {
                        onCategorySelectionChange(it)
                    }
                )
            }
        }
        Divider(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(1.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LowerPanelPreview() {
    LowerPanel(
        navController = NavHostController(LocalContext.current),
        menuItems = listOf(
            MenuItemRoom(
                image = "",
                price = 12.99,
                description = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago",
                title = "Greek Salad",
                id = 1,
                category = "Starters"
            )
        ),
        onEvent = {},
        menuItemsFiltered = listOf()
    )
}