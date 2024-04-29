package com.example.devicetracking.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.repository.ParentRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class ParentRepositoryImpl:ParentRepository {

    private val auth = Firebase.auth
    val database = Firebase.database
    override fun createParent(parent: Parent, password:String, isCreateProfile: MutableState<Boolean>):Boolean {
        val ref = database.getReference("Users")

        var success = false

        auth.createUserWithEmailAndPassword(parent.email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful){

                }else{
                    ref.child("parent").child(auth.currentUser!!.uid).setValue(parent)

                    isCreateProfile.value = true
                }
            }

        return success
    }

    override suspend fun getParent(parentId:String, parent: MutableState<Parent>) {

        val ref = database.getReference("Users")

        ref.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.i("test", parentId)

                    try {
                        val p:Parent? = snapshot.child("parent").child(parentId).getValue(Parent::class.java)!!

                        if(p != null){
                            parent.value = p
                        }

                    }catch (e:Exception){


                    }





                }


                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }


            }
        )




    }

    override fun addChildToParent(parent: Parent) {
        val ref = database.getReference("Users")

        ref.child("parent").child(auth.currentUser!!.uid).child("children").setValue(parent.children)
    }

    override suspend fun getChildren(childrenList:List<String>):SnapshotStateList<Child> {
        val list = mutableStateListOf<Child>()
        val ref = database.getReference("Users")


        object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childID in childrenList){
                    list.add(snapshot.child("Users").child("children").child(childID).getValue(Child::class.java)!!)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }







        return list
    }
}