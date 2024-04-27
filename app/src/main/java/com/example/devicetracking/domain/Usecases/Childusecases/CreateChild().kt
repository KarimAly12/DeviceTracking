package com.example.devicetracking.domain.Usecases.Childusecases

import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.repository.ChildRepository

class CreateChild(
    private val childRepository: ChildRepository
) {


    suspend operator fun invoke(child: Child, password:String){
        childRepository.createChild(child, password)
    }

}