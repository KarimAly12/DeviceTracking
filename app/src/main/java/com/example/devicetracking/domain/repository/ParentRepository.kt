package com.example.devicetracking.domain.repository

import com.example.devicetracking.domain.model.Parent

interface ParentRepository {

    fun createParent(parent: Parent, password:String)
    fun getParent()

}