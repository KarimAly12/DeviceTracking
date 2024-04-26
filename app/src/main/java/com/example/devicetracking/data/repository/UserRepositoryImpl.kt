package com.example.devicetracking.data.repository

import android.app.Application
import android.util.Log
import com.example.devicetracking.domain.model.User
import com.example.devicetracking.domain.repository.UserRepository

import com.example.flightdelivery.Presentation.CreateProfile.CreateProfileViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

class UserRepositoryImpl(private val application: Application) : UserRepository {

    private val auth = Firebase.auth

    val database = Firebase.database


    override fun createNewUser(user: User) {

        val ref = database.getReference("Users")
        ref.child(user.uid).setValue(user)
    }

    override suspend fun signUp(user:User, password:String, cpViewModel: CreateProfileViewModel):Boolean {


        var isSuccess = false
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener{
            if(!it.isSuccessful){

                cpViewModel.signUpSuccess.value = false


            }else{

                val user = User(auth.currentUser!!.uid, user.firstName, user.lastName, user.email)
                createNewUser(user)

                cpViewModel.signUpSuccess.value = true

            }
        }




        return isSuccess
    }
}