package com.example.devicetracking.domain.Usecases

import android.content.Context
import com.example.devicetracking.domain.model.User
import com.example.devicetracking.domain.repository.UserRepository

import com.example.flightdelivery.Presentation.CreateProfile.CreateProfileViewModel

class Signup(private val repository: UserRepository) {

    suspend operator fun invoke(user: User, password:String, createProfileViewModel: CreateProfileViewModel):Boolean{
        return repository.signUp(user, password, createProfileViewModel)
    }
}