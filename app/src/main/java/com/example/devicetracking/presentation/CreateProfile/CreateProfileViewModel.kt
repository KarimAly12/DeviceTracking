package com.example.flightdelivery.Presentation.CreateProfile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.data.repository.ChildRepositoryImpl
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.repository.ChildRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  CreateUserViewModel @Inject constructor(

    val childRepository: ChildRepository,
    //val childUsecases: ChildUsecases,
    val parentUsecases: ParentUsecases,
    //val userAuthRepository: UserAuthRepository,
    savedStateHandle: SavedStateHandle
):ViewModel() {




    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var userType = mutableStateOf("")


//    fun createUser(){
//        if (userType.value == CHILD){
//
//            viewModelScope.launch {
//                val child = ChildObject("1", firstName.value, lastName.value, false)
//                childRepository.createChild(child)
//            }
//
//        }
//    }
}