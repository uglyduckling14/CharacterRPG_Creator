package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.repositories.UserRepository
import kotlinx.coroutines.*

@Composable
fun SplashScreen(navHostController: NavHostController) {

    LaunchedEffect(true) {
        val loginStatusCheck = async {
            UserRepository.isUserLoggedIn()
        }
        // wait for 3 seconds or until the login check is
        // done before navigating
        delay(3000)
        //check if user is logged in
        val result: Boolean = loginStatusCheck.await()
        navHostController.navigate(
            if (!result) Routes.launchNavigation.route else Routes.appNavigation.route) {
            // makes it so that we can't get back to the
            // splash screen by pushing the back button
            popUpTo(navHostController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "RPG Creator",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Esperanza Hauptman",
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
    }
}