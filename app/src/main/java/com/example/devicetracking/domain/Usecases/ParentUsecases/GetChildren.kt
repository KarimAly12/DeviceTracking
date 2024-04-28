package com.example.devicetracking.domain.Usecases.ParentUsecases

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.repository.ParentRepository

class GetChildren(
    private val repository: ParentRepository
) {


    suspend operator fun invoke(childrenList:List<String>): SnapshotStateList<Child>  {

        return repository.getChildren(childrenList)
    }
}