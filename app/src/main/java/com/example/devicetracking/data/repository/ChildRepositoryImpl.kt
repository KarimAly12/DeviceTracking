package com.example.devicetracking.data.repository

import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class ChildRepositoryImpl : ChildRepository {

    private val auth = Firebase.auth
    val database = Firebase.database
    override fun createChild(child: Child, password:String) {

        val ref = database.getReference("Users")

        auth.createUserWithEmailAndPassword(child.email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful){

                }else{
                    ref.child("children").child(auth.currentUser!!.uid).setValue(child)

                }
            }
    }

    override fun getChild() {
        TODO("Not yet implemented")
    }


}