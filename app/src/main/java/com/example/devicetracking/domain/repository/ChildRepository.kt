package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ChildLocation
import com.google.firebase.database.ValueEventListener

interface ChildRepository {

    fun createChild(child: ChildObject)
    suspend fun getChild(childId:String, child: MutableState<ChildObject>)
    fun updateChildLocation(childId: String, childLocation: ChildLocation,)

    fun updateChild(childID: String, child: ChildObject)


    suspend fun getChildLocation(childId: String, valueEventListener: ValueEventListener) : ChildLocation
}