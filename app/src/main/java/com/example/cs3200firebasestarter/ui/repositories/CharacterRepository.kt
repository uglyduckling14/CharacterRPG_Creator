package com.example.cs3200firebasestarter.ui.repositories

import com.example.cs3200firebasestarter.ui.models.Character_
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object CharacterRepository {
    val db = Firebase.firestore

    private val characterCache = mutableListOf<Character_>()
    private var cacheInitialized = false
    suspend fun getCharacters(): List<Character_>{
        if(!cacheInitialized){
            val snapshot = db.collection("characters")
                .whereEqualTo("userId", UserRepository.getCurrentUserId())
                .get()
                .await()
            characterCache.addAll(snapshot.toObjects())
            cacheInitialized = true
        }
        return characterCache
    }
    suspend fun createCharacter(
        name: String,
        age: Int,
        race: String,
        class_: String,
        height: Int,
        gender: String,
        description: String
    ): Character_{
        val doc = db.collection("characters").document()

        val character = Character_(
            name = name,
            age = age,
            race = race,
            class_ = class_,
            height = height,
            gender = gender,
            description = description,
            id = doc.id,
            userId = UserRepository.getCurrentUserId())
        doc.set(character).await()
        characterCache.add(character)
        return character
    }
    suspend fun updateCharacter(character: Character_){
        Firebase.firestore
            .collection("characters")
            .document(character.id!!)
            .set(character)
            .await()

        val oldChar = characterCache.indexOfFirst {
            it.id == character.id
        }
        characterCache[oldChar] = character
    }
}