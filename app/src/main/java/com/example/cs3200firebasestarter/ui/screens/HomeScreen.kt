package com.example.cs3200firebasestarter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cs3200firebasestarter.ui.components.CharListItem
import com.example.cs3200firebasestarter.ui.viewmodels.HomeViewModel


@Composable
fun HomeScreen(navHostController: NavHostController) {
    val viewModel: HomeViewModel = viewModel()
    val state = viewModel.uiState
    val scope = rememberCoroutineScope()

    LaunchedEffect(true){
        viewModel.getCharacters()
    }

    LazyColumn{
        items(state.characters, key = {it.id !!}){character ->
            CharListItem(
                name = character.name,
                race = character.race,
                class_ = character.class_,
                onEditPressed = { navHostController.navigate("editcharacter?id=${character.id}")} )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}