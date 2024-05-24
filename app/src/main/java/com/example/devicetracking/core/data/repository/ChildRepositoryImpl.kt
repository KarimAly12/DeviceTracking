package com.example.devicetracking.core.data.repository

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.ChildLocation
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ChildLocationObject
import com.example.devicetracking.core.domain.repository.ChildRepository
import com.google.android.gms.common.api.ApiException

class ChildRepositoryImpl : ChildRepository {
    override  suspend fun createChild(child: ChildObject) {


        val c = convertChildObjectToChild(child)

        try {

            Amplify.API.mutate(ModelMutation.create(c),
                {
                    Log.i("MyAmplifyApp", it.errors.toString())

                },
                {
                    Log.i("MyAmplifyApp", it.message.toString())
                }
            )
        }catch (e: Exception){

            Log.e("CHILD_REPOSITORY", e.message.toString())
        }


    }

    override fun isChildExist(email:String, onChildFound: (Boolean) -> Unit){

        try{
            Amplify.API.query(
                ModelQuery[Child::class.java, email],
                {child ->
                    if(child.data != null){
                        onChildFound(true)
                    }else{
                        onChildFound(false)
                    }

                },
                {

                }
            )
        }catch (error: ApiException){
            Log.e("CHILD_FINDING", error.message.toString())
        }

    }

    override fun getChild(email: String, onChildFound: (ChildObject) -> Unit) {
        try{
            Amplify.API.query(
                ModelQuery[Child::class.java, email],
                {childResponse ->
                    onChildFound(convertChildToChildObject(childResponse.data))

                },
                {

                }
            )
        }catch (error: ApiException){
            Log.e("CHILD_REPOSITORY", error.message.toString())
        }


    }



    override suspend fun updateChild(child: ChildObject, onChildUpdated: (ChildObject) -> Unit) {

        val c = convertChildObjectToChild(child)
        try{
            Amplify.API.mutate(ModelMutation.update(c),
                {
                    onChildUpdated(convertChildToChildObject(it.data))
                },
                {error->
                    Log.e("CHILD_REPOSITORY", error.message.toString())
                }
            )


        }catch (error:Exception){
            Log.e("CHILD_REPOSITORY", error.message.toString())
        }

    }


    private fun convertChildToChildObject(child: Child): ChildObject {

        return ChildObject(
            child.childEmail,
            child.firstName,
            child.lastName,
            ChildLocationObject(child.location.latitude, child.location.longitude),
            child.inTrip,
            )
    }

    private fun convertChildObjectToChild(child: ChildObject): Child {
        val cLocation = ChildLocation.builder()
            .latitude(child.childLocationObject.latitude)
            .longitude(child.childLocationObject.longitude)
            .build()

        return Child.builder()
            .childEmail(child.email)
            .firstName(child.firstName)
            .lastName(child.lastName)
            .inTrip(child.inTrip)
            .location(cLocation)
            .build()

    }

}