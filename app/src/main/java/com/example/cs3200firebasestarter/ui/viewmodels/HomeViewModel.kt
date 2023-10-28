package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.models.Character_
import com.example.cs3200firebasestarter.ui.repositories.CharacterRepository

class HomeScreenState{
    val _characters = mutableListOf<Character_>()
    val characters: List<Character_> get() = _characters
}

class HomeViewModel(application: Application): AndroidViewModel(application) {
    val uiState = HomeScreenState()
    suspend fun getCharacters(){
        val characters = CharacterRepository.getCharacters()
        uiState._characters.clear()
        uiState._characters.addAll(characters)
    }

}