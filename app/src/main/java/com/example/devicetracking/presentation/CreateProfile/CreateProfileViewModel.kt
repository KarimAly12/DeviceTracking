package com.example.flightdelivery.Presentation.CreateProfile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.UserUsecases
import com.example.devicetracking.domain.model.User

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  CreateProfileViewModel @Inject constructor(

    val userUsecases: UserUsecases
):ViewModel() {

    var passwordWeak = false
    var passwordMedium = false
    var passwordStrong = false


    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var signUpSuccess = mutableStateOf(false)





    fun signUp(){

        val user = User("", firstName.value, lastName.value, email.value)

        signUpSuccess.value = false
        viewModelScope.launch {
            signUpSuccess.value = userUsecases.signup(user, password.value, this@CreateProfileViewModel)
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