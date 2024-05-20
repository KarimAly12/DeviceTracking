package com.example.devicetracking.domain.repository

import androidx.compose.runtime.MutableState
import com.amplifyframework.api.graphql.GraphQLResponse
import com.amplifyframework.datastore.generated.model.Child
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ChildLocationObject
import com.google.firebase.database.ValueEventListener

interface ChildRepository {

    fun createChild(child: ChildObject)

    fun isChildExist(email:String, onChildFound: (Boolean) -> Unit)
    fun getChild(email: String, onChildFound: (ChildObject) -> Unit)
    fun updateChildLocation(childId: String, childLocation: ChildLocationObject,)

    fun updateChild(childID: String, child: ChildObject)


    suspend fun getChildLocation(childId: String, valueEventListener: ValueEventListener) : ChildLocationObject
}