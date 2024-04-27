package com.example.devicetracking.domain.Usecases.ParentUsecases

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.repository.ParentRepository

class GetParent(private val parentRepository: ParentRepository) {
    suspend operator fun invoke(parentID:String, parent: MutableState<Parent>){

        parentRepository.getParent(parentID, parent)
    }
}