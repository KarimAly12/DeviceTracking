package com.example.devicetracking.core.domain.repository

import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ChildLocationObject
import com.google.firebase.database.ValueEventListener

interface ChildRepository {

    suspend fun createChild(child: ChildObject)

    fun isChildExist(email:String, onChildFound: (Boolean) -> Unit)
     fun getChild(email: String, onChildFound: (ChildObject) -> Unit)

    suspend fun updateChild(child: ChildObject, onChildUpdated: (ChildObject) -> Unit)


}