package com.example.devicetracking.data.repository

import android.util.Log
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.ChildLocation
import com.amplifyframework.datastore.generated.model.Parent
import com.amplifyframework.datastore.generated.model.ParentChild
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.model.ParentObject
import com.example.devicetracking.domain.repository.ChildRepository
import com.example.devicetracking.domain.repository.ParentChildRepository
import com.example.devicetracking.domain.repository.ParentRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParentChildRepositoryImpl @Inject constructor(
    private val childRepository: ChildRepository,
    private val parentRepository: ParentRepository

):ParentChildRepository {
    override suspend fun addParentChild(childEmail: String, parentEmail: String) {

       try {

           childRepository.getChild(childEmail){c ->
               parentRepository.getParent(parentEmail){p ->

                   val parentChild = ParentChild
                       .builder()
                       .child(convertChildObjectToChild(c))
                       .parent(convertParentObjectToParent(p))
                       .build()

                   Amplify.API.mutate(ModelMutation.create(parentChild),
                       {
                           Log.i("PARENT_CHILD_REPOSITORY", it.errors.toString())

                       },
                       {
                           Log.i("PARENT_CHILD_REPOSITORY", it.message.toString())
                       }
                   )
               }
           }


       }catch(error:Exception){
           Log.i("PARENT_CHILD_REPOSITORY", error.message.toString())
       }


    }

    override suspend fun getParents(childEmail: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getChildren(parentEmail: String) {
        TODO("Not yet implemented")
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