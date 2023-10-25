package com.example.cs3200firebasestarter.ui.repositories

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

object UserRepository {
    suspend fun createUser(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun loginUser(email: String, password: String): Int {
        try{
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            return 0
        }catch(e :Exception){
            println(e)
            return 1;
        }

    }

    fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    fun isUserLoggedIn(): Boolean{
        return getCurrentUserId() != null
    }

    fun logout() {
        Firebase.auth.signOut()
    }
}