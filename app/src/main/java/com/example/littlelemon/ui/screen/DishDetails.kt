package com.example.littlelemon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.R
import com.example.littlelemon.data.repository.Dish
import com.example.littlelemon.ui.component.TopAppBar
import com.example.littlelemon.ui.navigation.NavEvent

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DishDetails(
    id: Int,
    navEvent: (NavEvent) -> Unit
) {
    val dish = Dish(
        id = 1,
        name = "Greek Salad",
        description = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago.",
        price = 12.99,
        imageResource = R.drawable.greeksalad.toString()
    )
    Column(
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TopAppBar(navEvent = navEvent)
        GlideImage(
            model = dish.imageResource,
            contentDescription = "Dish image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = dish.name,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = dish.description,
            style = MaterialTheme.typography.bodyMedium
        )
        Counter()
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.add_for) + " $${dish.price}",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun Counter() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        var counter by remember {
            mutableStateOf(1)
        }
        TextButton(
            onClick = {
                counter--
            }
        ) {
            Text(
                text = "-",
                style = MaterialTheme.typography.titleSmall
            )
        }
        Text(
            text = counter.toString(),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(16.dp)
        )
        TextButton(
            onClick = {
                counter++
            }
        ) {
            Text(
                text = "+",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview
@Composable
fun DishDetailsPreview() {
    DishDetails(id = 1, navEvent = {})
}
