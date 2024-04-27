package com.example.flightdelivery.Presentation.CreateProfile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.CreateParent.ParentUsecases
import com.example.devicetracking.domain.Usecases.UserUsecases
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.domain.model.User

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class  CreateProfileViewModel @Inject constructor(

    val childUsecases: ChildUsecases,
    val parentUsecases: ParentUsecases,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    var passwordWeak = false
    var passwordMedium = false
    var passwordStrong = false


    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var signUpSuccess = mutableStateOf(false)
    val type:String = checkNotNull(savedStateHandle["type"])
    val isCreateSuccess = mutableStateOf(false)





    fun signUp(){



        if(type == "children"){

            val child = Child(
                firstName.value, lastName.value, email.value, Pair(0.0f,0.0f),
            )
            viewModelScope.launch {
               childUsecases.createChild(child, password.value, isCreateSuccess)


            }

        }else{
            val parent = Parent(
                firstName.value, lastName.value, email.value
            )

            viewModelScope.launch {
                parentUsecases.createParent(parent, password.value, isCreateSuccess)

            }
        }



    }

    fun checkPasswordStrength(){
        checkPasswordUpperAndLowerCaseChar()
        checkPasswordLength()
        checkPasswordSpecialChar()
    }
    private fun checkPasswordLength(){
        passwordMedium = password.value.length >= 8
    }

    private fun checkPasswordUpperAndLowerCaseChar(){

        val regex = Regex("[a-zA-Z]")
        val hasUpperCase = regex.containsMatchIn(password.value)
        val hasLowerCase = regex.containsMatchIn(password.value)

        passwordWeak = hasUpperCase && hasLowerCase

    }

    private fun checkPasswordSpecialChar(){

        val hasNumber = password.value.any { it.isDigit() }
        val hasSpecialChar = password.value.any { it in setOf('&', '@', '$') }
        passwordStrong =  hasNumber && hasSpecialChar

    }


}