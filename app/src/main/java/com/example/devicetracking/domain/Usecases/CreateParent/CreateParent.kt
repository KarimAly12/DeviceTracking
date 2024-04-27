package com.example.devicetracking.domain.Usecases.CreateParent

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.model.User
import com.example.devicetracking.domain.repository.ParentRepository
import com.example.devicetracking.domain.repository.UserRepository


class CreateParent(private val parentRepository: ParentRepository) {

    suspend operator fun invoke(parent: Parent, password:String, isCreateProfile: MutableState<Boolean>):Boolean{

        return parentRepository.createParent(parent, password, isCreateProfile)
    }
}