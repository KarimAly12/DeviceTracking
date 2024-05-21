package com.example.devicetracking.domain.repository

import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ChildLocationObject
import com.google.firebase.database.ValueEventListener

interface ChildRepository {

    fun createChild(child: ChildObject)

    fun isChildExist(email:String, onChildFound: (Boolean) -> Unit)
    fun getChild(email: String, onChildFound: (ChildObject) -> Unit)
    fun updateChildLocation(childId: String, childLocation: ChildLocationObject,)

    suspend fun updateChild(child: ChildObject, onChildUpdated: (ChildObject) -> Unit)


    suspend fun getChildLocation(childId: String, valueEventListener: ValueEventListener) : ChildLocationObject
}