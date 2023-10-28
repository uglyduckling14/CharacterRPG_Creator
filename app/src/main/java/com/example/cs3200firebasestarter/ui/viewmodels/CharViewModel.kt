package com.example.cs3200firebasestarter.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.cs3200firebasestarter.ui.repositories.CharacterRepository

class CreateScreenState{
    var name by mutableStateOf("")
    var age by mutableIntStateOf(0)
    var race by mutableStateOf("")
    var class_ by mutableStateOf("")
    var height by mutableIntStateOf(0)
    var gender by mutableStateOf("")
    var description by mutableStateOf("")
    var error by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
}


//Todomodification
class CharViewModel(application: Application): AndroidViewModel(application) {
    val uiState = CreateScreenState()
    var id:String? = null
    suspend fun setupInitialState(id: String?){
        if(id == null || id == "new") return
        this.id = id
        val character = CharacterRepository.getCharacters().find{it.id == id} ?: return

        uiState.age = character.age?:0
        uiState.name = character.name?:""
        uiState.race = character.race?:""
        uiState.class_ = character.class_?:""
        uiState.height = character.height?:0
        uiState.gender = character.gender?:""
        uiState.description = character.description?:""
    }
    suspend fun saveChar(){
        validate()
        if(uiState.error){
            return
        }
        if(id == null){ //create new
            CharacterRepository.createCharacter(
                uiState.name,
                uiState.age,
                uiState.race,
                uiState.class_,
                uiState.height,
                uiState.gender,
                uiState.description
            )
        } else{ // update
            val character = CharacterRepository.getCharacters().find{it.id == id}
            if (character != null) {
                CharacterRepository.updateCharacter(
                    character.copy(
                        name = uiState.name,
                        age = uiState.age,
                        race = uiState.race,
                        class_ = uiState.class_,
                        height = uiState.height,
                        gender = uiState.gender,
                        description = uiState.description
                    )
                )
            }
        }
    }
    private fun validate(){
        if(uiState.name.isEmpty()){
            uiState.error = true
            uiState.errorMessage = "Name cannot be blank"
        }
        if(uiState.age == 0){
            uiState.error = true
            uiState.errorMessage = "Age cannot be 0"
        }
        if(uiState.race.isEmpty()){
            uiState.error = true
            uiState.errorMessage = "Race cannot be blank"
        }
        if(uiState.class_.isEmpty()){
            uiState.error = true
            uiState.errorMessage = "Class cannot be blank"
        }
        if(uiState.height == 0){
            uiState.error = true
            uiState.errorMessage = "Name cannot be 0"
        }
        if(uiState.gender.isEmpty()){
            uiState.error = true
            uiState.errorMessage = "Gender cannot be blank"
        }
        if(uiState.description.isEmpty()){
            uiState.error = true
            uiState.errorMessage = "Description cannot be blank"
        }
    }
}