package com.example.devicetracking.core.domain.repository

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.amplifyframework.datastore.generated.model.Parent
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ParentObject

interface ParentRepository {


    suspend fun createParent(parent: ParentObject)
    suspend fun getParent(parentEmail:String, onParentFound: (ParentObject) -> Unit)

    fun isParentExist(email:String, onParentFound: (Boolean) -> Unit)

    suspend fun addChild(childEmail:String, parentObj:ParentObject)

    suspend fun getChildren(childrenList:List<String>)



}