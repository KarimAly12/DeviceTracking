package com.example.devicetracking.domain.Usecases

import com.example.devicetracking.domain.Usecases.CreateParent.CreateParent

data class UserUsecases(
    val signup: Signup,
    val createUser: CreateParent
)