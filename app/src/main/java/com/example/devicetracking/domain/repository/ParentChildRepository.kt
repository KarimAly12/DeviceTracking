package com.example.devicetracking.domain.repository

interface ParentChildRepository {

    suspend fun addParentChild(childEmail:String, parentEmail:String)
    suspend fun getParents(childEmail:String)

    suspend fun getChildren(parentEmail:String)

}