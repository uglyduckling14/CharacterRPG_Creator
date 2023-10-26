package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.FormField
import com.example.cs3200firebasestarter.ui.navigation.Routes
import com.example.cs3200firebasestarter.ui.viewmodels.CharViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateScreen(navHostController: NavHostController) {
    val viewModel: CharViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()

    LaunchedEffect(true){

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround) {
        Surface(shadowElevation = 2.dp) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Text(text = "New Character", style = MaterialTheme.typography.headlineSmall)
                FormField(
                    value = state.name,
                    onValueChange = { state.name = it },
                    placeholder = { Text("Name") },
                    error = state.error
                )
                FormField(
                    value = state.age.toString(),
                    onValueChange = {
                        if(it.isNotBlank()) {
                        state.age = it.toInt()
                        }
                                    },
                    placeholder = { Text("Age") },
                    error = state.error
                )
                FormField(
                    value = state.race,
                    onValueChange = { state.race = it },
                    placeholder = { Text("Race") },
                    error = state.error
                )
                FormField(
                    value = state.class_,
                    onValueChange = { state.class_ = it },
                    placeholder = { Text("Class") },
                    error = state.error
                )
                FormField(
                    value = state.height.toString(),
                    onValueChange = {
                        if(it.isNotBlank()) {
                            state.height = it.toInt()
                        }},
                    placeholder = { Text("Height") },
                    error = state.error
                )
                FormField(
                    value = state.gender,
                    onValueChange = { state.gender = it },
                    placeholder = { Text("Gender") },
                    error = state.error
                )
                FormField(
                    value = state.description,
                    onValueChange = { state.description = it },
                    placeholder = { Text("Description") },
                    error = state.error
                )
                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    TextButton(onClick = { navHostController.popBackStack() }) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { scope.launch {
                        viewModel.saveChar()
                        navHostController.navigate(Routes.home.route)
//                        if (CharacterRepository.getCurrentUserId() != null) {
//                            navHostController.navigate(Routes.appNavigation.route) {
//                                popUpTo(navHostController.graph.id) {
//                                    inclusive = true
//                                }
//                            }
//                        }
                    } }, elevation = null) {
                        Text(text = "Save")
                    }
                }
                Text(
                    text = state.errorMessage,
                    style = TextStyle(color = MaterialTheme.colorScheme.error),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}