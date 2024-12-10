package com.example.littlelemon.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.navigation.Destinations
import com.example.littlelemon.R
import com.example.littlelemon.data.model.User
import com.example.littlelemon.presentation.viewmodel.HomeEvent


@Composable
fun Onboarding(
    navController: NavHostController,
    onEvent: (HomeEvent) -> Unit
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var registrationMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp)
                .padding(vertical = 32.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFF495E57))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color(0xFFEDEFEE)
            )
        }
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
            value = firstName,
            onValueChange = { firstName = it },
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
            value = lastName,
            onValueChange = { lastName = it },
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
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        ElevatedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 50.dp),
            onClick = {
                onEvent(HomeEvent.Login(User(firstName, lastName, email)))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),
                contentColor = Color(0xFF000000)
            )
        ) {
            Text(
                text = "Register"
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = registrationMessage,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        Onboarding(
            navController = NavHostController(LocalContext.current),
            onEvent = {}
        )
    }
}