package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.Parent

interface ParentRepository {

    fun createParent(parent: Parent, password:String, isCreateProfile: MutableState<Boolean>):Boolean
    suspend fun getParent(parentId:String, parent: MutableState<Parent>)

    fun addChildToParent(childId:Parent)

    suspend fun getChildren(childrenList:List<String>): SnapshotStateList<ChildObject>



}