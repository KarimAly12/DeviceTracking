package com.example.devicetracking.presentation.ParentScreen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.Parent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParentViewModel @Inject constructor(
    val parenteUsecases: ParentUsecases
):ViewModel() {


    val auth = Firebase.auth

    var parent = mutableStateOf(Parent("", "", ""))
    var children:MutableList<Child> = mutableStateListOf()


    init {

        viewModelScope.launch {
            parenteUsecases.getParent(auth.currentUser!!.uid, parent)

        }
    }


    fun getChildren(){
        children.clear()
        viewModelScope.launch {
            //parenteUsecases.getParent(auth.currentUser!!.uid, parent)
            children = parenteUsecases.getChildren(parent.value.children)
            Log.i("test", children.size.toString())

        }
    }

    fun addChild(childId:String){

        parent.value.children.add(childId)

        viewModelScope.launch {

            parenteUsecases.addChildToParent(parent.value)


        }

    }
}