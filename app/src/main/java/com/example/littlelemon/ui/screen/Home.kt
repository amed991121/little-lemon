package com.example.littlelemon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.ui.navigation.Destinations
import com.example.littlelemon.R
import com.example.littlelemon.presentation.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.littlelemon.data.local.MenuItemRoom

@Composable
fun Home(navController: NavHostController) {

    val homeViewModel: HomeViewModel = viewModel()
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(48.dp)
                    .padding(16.dp)
                    .clickable { navController.navigate(Destinations.Profile.route) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.hero_image),
            contentDescription = "Hero Image",
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Little Lemon", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Chicago", style = MaterialTheme.typography.titleMedium)
        Text(text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist")

        TextField(
            value = searchPhrase,
            onValueChange = { searchPhrase = it },
            placeholder = { Text("Enter search phrase") },
            leadingIcon = {
                IconButton(onClick = { /* TODO: Implement search action */ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }


}

@Composable
fun MenuItems(items: List<MenuItemRoom>) {
    Column {
        items.forEach { item ->
            MenuItem(item)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemRoom) {
    Row {
        GlideImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier.size(100.dp)
        )
        Column {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.price.toString(), style = MaterialTheme.typography.titleSmall)
            Text(text = item.description)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(
        navController = NavHostController(
            context = LocalContext.current
        )
    )
}