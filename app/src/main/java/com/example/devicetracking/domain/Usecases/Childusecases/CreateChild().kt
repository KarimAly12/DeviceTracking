package com.example.devicetracking.domain.Usecases.Childusecases

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.repository.ChildRepository

class CreateChild(
    private val childRepository: ChildRepository
) {


    suspend operator fun invoke(child: Child, password:String, isCreateProfile:MutableState<Boolean>):Boolean{
        return childRepository.createChild(child, password, isCreateProfile)
    }

}