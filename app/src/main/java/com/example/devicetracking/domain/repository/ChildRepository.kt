package com.example.devicetracking.domain.repository

import com.example.devicetracking.domain.model.Child

interface ChildRepository {

    fun createChild(child: Child, password:String)
    fun getChild()
}