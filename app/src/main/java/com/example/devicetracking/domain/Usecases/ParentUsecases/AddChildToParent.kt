package com.example.devicetracking.domain.Usecases.ParentUsecases

import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.repository.ParentRepository

class AddChildToParent(private val repository: ParentRepository) {

    suspend operator fun invoke(parent: Parent) {
        repository.addChildToParent(parent)
    }
}