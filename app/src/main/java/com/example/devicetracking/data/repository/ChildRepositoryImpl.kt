package com.example.devicetracking.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.ChildLocation
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChildRepositoryImpl : ChildRepository {
    override fun createChild(child: Child) {

    }

    override suspend fun getChild(childId: String, child: MutableState<Child>) {
        TODO("Not yet implemented")
    }

    override fun updateChildLocation(childId: String, childLocation: ChildLocation) {
        TODO("Not yet implemented")
    }

    override fun updateChild(childID: String, child: Child) {
        TODO("Not yet implemented")
    }

    override suspend fun getChildLocation(
        childId: String,
        valueEventListener: ValueEventListener
    ): ChildLocation {
        TODO("Not yet implemented")
    }


}