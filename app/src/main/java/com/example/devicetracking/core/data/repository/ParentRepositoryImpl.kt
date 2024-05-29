package com.example.devicetracking.core.data.repository

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.api.graphql.model.ModelSubscription
import com.amplifyframework.core.Amplify
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

        try {

            Amplify.API.query(ModelQuery[Parent::class.java, parentEmail],
                {parentResponse->
                if (parentResponse.data != null){

                    onParentFound(convertParentToParentObject(parentResponse.data))
                }

                },
                {}
            )


        }catch (e:Exception){

            Log.i("PARENT_REPOSITORY", e.message.toString())
        }
    }


    override suspend fun addChild(childEmail:String, parentObj: ParentObject){
        Amplify.API.query(
            ModelQuery[Child::class.java, childEmail],
            {
                val parentChild = ParentChild.builder()
                    .child(it.data)
                    .parent(convertParentObjectToParent(parentObj))
                    .build()

                Amplify.API.mutate(
                    ModelMutation.create(parentChild),
                    {},
                    {}
                )

            },
            {}
        )

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