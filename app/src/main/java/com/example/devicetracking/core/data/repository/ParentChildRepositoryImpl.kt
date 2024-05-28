package com.example.devicetracking.core.data.repository

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.ChildLocation
import com.amplifyframework.datastore.generated.model.Parent
import com.amplifyframework.datastore.generated.model.ParentChild
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ParentObject
import com.example.devicetracking.core.domain.repository.ChildRepository
import com.example.devicetracking.core.domain.repository.ParentChildRepository
import com.example.devicetracking.core.domain.repository.ParentRepository
import kotlinx.coroutines.awaitAll
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParentChildRepositoryImpl @Inject constructor(


): ParentChildRepository {
    override suspend fun addParentChild(childEmail: String, parentEmail: String) {

       try {


           Amplify.API.query(
               ModelQuery[Child::class.java, childEmail],
               {c ->
                   Amplify.API.query(
                       ModelQuery[Parent::class.java, parentEmail],
                       {p ->

                           val parentChild = ParentChild
                               .builder()
                               .child(c.data)
                               .parent(p.data)
                               .build()

                           Amplify.API.mutate(ModelMutation.create(parentChild),
                               {
                                   Log.i("PARENT_CHILD_REPOSITORY", it.errors.toString())

                               },
                               {
                                   Log.i("PARENT_CHILD_REPOSITORY", it.message.toString())
                               }
                           )


                       },
                       {}
                   )


               },
               {}
           )



       }catch(error:Exception){
           Log.i("PARENT_CHILD_REPOSITORY", error.message.toString())
       }


    }

    override suspend fun getParents(childEmail: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getChildren(parentEmail: String) {
        try {


            Amplify.API.query(ModelQuery.list(ParentChild::class.java,
                ParentChild.PARENT.contains(parentEmail)),

                {

                    it.data.forEach {
                        Log.i("test", it.id)
                    }

                },
                {}



            )


        }catch (error:Exception){
            Log.i("CHILD_REPOSITORY", error.message.toString())
        }
    }


    private fun convertParentObjectToParent(parent: ParentObject): Parent {

        return Parent.builder()
            .parentEmail(parent.email)
            .firstName(parent.firstName)
            .lastName(parent.lastName)
            .build()
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