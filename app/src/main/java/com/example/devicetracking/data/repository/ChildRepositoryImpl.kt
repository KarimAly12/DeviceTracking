package com.example.devicetracking.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ChildRepositoryImpl : ChildRepository {

    private val auth = Firebase.auth
    val database = Firebase.database
    override fun createChild(child: Child, password:String, isCreateProfile: MutableState<Boolean>):Boolean {

        var success = false
        val ref = database.getReference("Users")

        auth.createUserWithEmailAndPassword(child.email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful){

                }else{
                    child.childID = auth.currentUser!!.uid
                    ref.child("children").child(auth.currentUser!!.uid).setValue(child)
                    isCreateProfile.value = true
                }
            }

        return success
    }

    override suspend fun getChild(childId:String, child: MutableState<Child>) {

        val ref = database.getReference("Users")

        ref.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.i("test", childId)


                    try {

                        child.value = snapshot.child("children").child(childId).getValue(Child::class.java)!!

                        Log.i("test", child.value.location.toString())



                    }catch (_:Exception){

                    }



                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("test", error.details)
                }

            }
        )


    }
    override fun updateChildLocation(childId: String, location:Pair<Double,Double>) {
        val ref = database.getReference("Users")

        ref.child("children").child(childId).child("location").setValue(location)

    }

    override fun updateChild(childId: String, child: Child) {
        val ref = database.getReference("Users")
        ref.child("children").child(childId).setValue(child)



    }


}