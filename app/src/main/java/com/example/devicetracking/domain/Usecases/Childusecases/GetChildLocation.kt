package com.example.devicetracking.domain.Usecases.Childusecases

import com.example.devicetracking.domain.model.ChildLocation
import com.example.devicetracking.domain.repository.ChildRepository

class GetChildLocation(private val repository: ChildRepository) {

    suspend operator fun invoke(childID:String): ChildLocation{
        return repository.getChildLocation(childID)
    }
}