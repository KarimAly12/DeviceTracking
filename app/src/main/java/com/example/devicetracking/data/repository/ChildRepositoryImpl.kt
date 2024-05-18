package com.example.devicetracking.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ChildLocation
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.firebase.database.ValueEventListener

class ChildRepositoryImpl : ChildRepository {
    override fun createChild(child: ChildObject) {
        val c = Child.builder()
            .firstName(child.firstName)
            .lastName(child.lastName)
            .inTrip(child.inTrip)
            .build()

        Amplify.API.mutate(ModelMutation.create(c),
            {
                Log.i("MyAmplifyApp", it.errors.toString())

            },
            {
                Log.i("MyAmplifyApp", it.message.toString())
            }
        )

    }

    override suspend fun getChild(childId: String, child: MutableState<ChildObject>) {
        TODO("Not yet implemented")
    }

    override fun updateChildLocation(childId: String, childLocation: ChildLocation) {
        TODO("Not yet implemented")
    }

    override fun updateChild(childID: String, child: ChildObject) {
        TODO("Not yet implemented")
    }

    override suspend fun getChildLocation(
        childId: String,
        valueEventListener: ValueEventListener
    ): ChildLocation {
        TODO("Not yet implemented")
    }


}