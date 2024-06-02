package com.example.devicetracking.core.data.repository

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.api.graphql.model.ModelSubscription
import com.amplifyframework.kotlin.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.Parent
import com.amplifyframework.datastore.generated.model.ParentChild
import com.example.devicetracking.core.domain.model.ChildLocationObject
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ParentObject
import com.example.devicetracking.core.domain.repository.ParentRepository
import com.google.android.gms.common.api.ApiException

class ParentRepositoryImpl: ParentRepository {


    override suspend fun createParent(parent: ParentObject) {

       try {

           Amplify.API.mutate(ModelMutation.create(convertParentObjectToParent(parent)))


       }catch (error:Exception){
           Log.e("PARENT_REPOSITORY", "create parent error ", error)
       }

    }
    override suspend fun getParent(parentEmail:String, onParentFound: (ParentObject) -> Unit) {

        try {
            Amplify.API.mutate(ModelQuery[Parent::class.java, parentEmail])
            

        }catch (error:Exception){
            Log.e("PARENT_REPOSITORY", "get parent error ", error)

        }

    }


    override suspend fun addChild(childEmail:String, parentObj: ParentObject){


    }
    override fun isParentExist(email:String, onParentFound: (Boolean) -> Unit) {

    }


    override suspend fun getChildren(childrenList:List<String>) {

    }



    private fun convertParentToParentObject(parent: Parent): ParentObject {
        return ParentObject(
            parent.parentEmail,
            parent.firstName,
            parent.lastName,

        )
    }
    private fun convertParentObjectToParent(parent: ParentObject): Parent {

       return Parent.builder()
           .parentEmail(parent.email)
           .firstName(parent.firstName)
           .lastName(parent.lastName)
           .build()


    }
}