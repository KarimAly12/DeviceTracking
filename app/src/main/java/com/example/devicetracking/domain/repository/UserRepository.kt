package com.example.devicetracking.domain.repository

import com.example.devicetracking.domain.model.User
import com.example.flightdelivery.Presentation.CreateProfile.CreateProfileViewModel

interface UserRepository {

    fun createNewUser(user: User, id:String)

    suspend fun signUp(user: User, password:String):Boolean

}