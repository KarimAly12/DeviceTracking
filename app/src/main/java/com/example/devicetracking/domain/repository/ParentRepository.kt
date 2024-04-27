package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Parent

interface ParentRepository {

    fun createParent(parent: Parent, password:String, isCreateProfile: MutableState<Boolean>):Boolean
    fun getParent()

}