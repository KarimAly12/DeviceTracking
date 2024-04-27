package com.example.devicetracking.data.repository

import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.repository.ParentRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database

class ParentRepositoryImpl:ParentRepository {

    private val auth = Firebase.auth
    val database = Firebase.database
    override fun createParent(parent: Parent, password:String) {
        val ref = database.getReference("Users")

        auth.createUserWithEmailAndPassword(parent.email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful){

                }else{
                    ref.child("parent").child(auth.currentUser!!.uid).setValue(parent)

                }
            }
    }

    override fun getParent() {
        TODO("Not yet implemented")
    }
}