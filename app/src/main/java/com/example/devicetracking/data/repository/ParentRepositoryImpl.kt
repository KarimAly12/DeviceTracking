package com.example.devicetracking.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.ChildLocation
import com.amplifyframework.datastore.generated.model.Parent
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ParentObject
import com.example.devicetracking.domain.repository.ParentRepository
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ParentRepositoryImpl:ParentRepository {


    override suspend fun createParent(parent: ParentObject) {
        Amplify.API.mutate(
            ModelMutation.create(
                convertParentObjectToParent(parent)
            ),
            {
                Log.i("PARENT_REPOSITORY", "create parent success")
            },
            {
                Log.e("PARENT_REPOSITORY", "create parent error ", it)
            }
        )


    }
    override fun getParent(parentEmail:String, onParentFound: (ParentObject) -> Unit) {

        

    }

    override fun isParentExist(email:String, onParentFound: (Boolean) -> Unit) {
        try {
            Amplify.API.query(
                ModelQuery[Parent::class.java, email],
                {parent ->
                    if(parent.data != null){
                        onParentFound(true)

                    }else{
                        onParentFound(false)
                    }

                },
                {

                }
            )
        } catch (error: ApiException) {

            Log.e("PARENT_REPOSITORY", "get parent error ", error)

        }
    }


    override suspend fun getChildren(childrenList:List<String>) {

    }


    private fun convertParentObjectToParent(parent: ParentObject): Parent {

       return Parent.builder()
           .parentEmail(parent.email)
           .firstName(parent.firstName)
           .lastName(parent.lastName)
           .build()


    }
}