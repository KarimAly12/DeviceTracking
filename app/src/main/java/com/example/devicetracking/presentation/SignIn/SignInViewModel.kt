package com.example.devicetracking.presentation.SignIn


import androidx.lifecycle.ViewModel
//import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    //val childUsecases: ChildUsecases,
    val parentUsecases: ParentUsecases,
):ViewModel() {

    var passwordWeak = false
    var passwordMedium = false
    var passwordStrong = false



    fun checkPasswordStrength(
        password:String

    ){
        checkPasswordUpperAndLowerCaseChar(password)
        checkPasswordLength(password)
        checkPasswordSpecialChar(password)
    }
    private fun checkPasswordLength( password:String){
        passwordMedium = password.length >= 8
    }

    private fun checkPasswordUpperAndLowerCaseChar( password:String){

        val regex = Regex("[a-zA-Z]")
        val hasUpperCase = regex.containsMatchIn(password)
        val hasLowerCase = regex.containsMatchIn(password)

        passwordWeak = hasUpperCase && hasLowerCase

    }

    private fun checkPasswordSpecialChar(password:String){

        val hasNumber = password.any { it.isDigit() }
        val hasSpecialChar = password.any { it in setOf('&', '@', '$') }
        passwordStrong =  hasNumber && hasSpecialChar

    }


}