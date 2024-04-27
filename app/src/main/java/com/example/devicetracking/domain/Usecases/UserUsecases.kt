package com.example.devicetracking.domain.Usecases

import com.example.devicetracking.domain.Usecases.ParentUsecases.CreateParent

data class UserUsecases(
    val signup: Signup,
    val createUser: CreateParent
)