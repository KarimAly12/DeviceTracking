package com.example.devicetracking.core.domain.repository

interface ParentChildRepository {

    suspend fun addParentChild(childEmail:String, parentEmail:String)
    suspend fun getParents(childEmail:String)

    suspend fun getChildren(parentEmail:String)

}