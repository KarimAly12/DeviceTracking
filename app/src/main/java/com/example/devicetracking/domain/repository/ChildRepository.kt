package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Child

interface ChildRepository {

    fun createChild(child: Child, password:String, isCreateProfile: MutableState<Boolean>):Boolean
    fun getChild()
}