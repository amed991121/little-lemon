package com.example.littlelemon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.navigation.Destinations
import com.example.littlelemon.R
import com.example.littlelemon.data.model.User
import com.example.littlelemon.presentation.viewmodel.HomeEvent
import androidx.compose.runtime.*


@Composable
fun Profile(
    user: User?,
    navController: NavHostController,
    onEvent: (HomeEvent) -> Unit
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        onEvent(HomeEvent.GetUser)

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp)
                .padding(vertical = 30.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp, horizontal = 15.dp),
            text = "Personal Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = "First Name",
            fontSize = 12.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = user?.firstName ?: "",
            onValueChange = {  },
            label = { Text("First Name") }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 30.dp),
            text = "Last Name",
            fontSize = 12.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = user?.lastName ?: "",
            onValueChange = {  },
            label = { Text("Last Name") }
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 30.dp),
            text = "Email",
            fontSize = 12.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            value = user?.email ?: "",
            onValueChange = {  },
            label = { Text("Email") }
        )

        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 50.dp),
            onClick = {
                onEvent(HomeEvent.Logout)
                navController.navigate(Destinations.Onboarding.route) {
                    popUpTo(Destinations.Onboarding.route) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),
                contentColor = Color(0xFF000000)
            )
        ) {
            Text("Log out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        navController = NavHostController(
            context = LocalContext.current
        ),
        onEvent = {},
        user = User("John", "Doe", "william.henry.harrison@example-pet-store.com")
    )
}