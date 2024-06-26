package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.ChildLocation
import com.google.firebase.database.ValueEventListener

interface ChildRepository {

    fun createChild(child: Child, password:String, isCreateProfile: MutableState<Boolean>):Boolean
    suspend fun getChild(childId:String, child: MutableState<Child>)
    fun updateChildLocation(childId: String, childLocation: ChildLocation,)

    fun updateChild(childID: String, child: Child)


    suspend fun getChildLocation(childId: String, valueEventListener: ValueEventListener) : ChildLocation
}