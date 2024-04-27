package com.example.devicetracking.presentation.Parent

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
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

    var parent = mutableStateOf<Parent>(Parent("", "", ""))

    init {

        viewModelScope.launch {
            parenteUsecases.getParent(auth.currentUser!!.uid, parent)

        }
    }

    fun addChild(childId:String){

        parent.value.children.add(childId)

        viewModelScope.launch {

            parenteUsecases.addChildToParent(parent.value)

        }

    }
}