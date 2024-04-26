package com.example.devicetracking.domain.Usecases

import com.example.devicetracking.domain.model.User
import com.example.devicetracking.domain.repository.UserRepository


class CreateUser(private val repository: UserRepository) {

    suspend operator fun invoke(user: User){

        repository.createNewUser(user)
    }
}