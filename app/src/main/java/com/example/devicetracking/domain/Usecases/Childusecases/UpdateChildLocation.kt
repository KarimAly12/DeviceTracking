package com.example.devicetracking.domain.Usecases.Childusecases

import com.example.devicetracking.domain.repository.ChildRepository

class UpdateChildLocation(val repository: ChildRepository) {


    suspend operator fun invoke(childId: String, location: Pair<Double,Double>) {

        repository.upadteChildLocation(childId, location)

    }
}