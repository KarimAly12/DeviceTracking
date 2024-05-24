package com.example.devicetracking.core.domain.Usecases.ParentUsecases

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.repository.ParentRepository

class GetChildren(
    private val repository: ParentRepository
) {


//    suspend operator fun invoke(childrenList:List<String>): SnapshotStateList<ChildObject>  {
//
//        return repository.getChildren(childrenList)
//    }
}