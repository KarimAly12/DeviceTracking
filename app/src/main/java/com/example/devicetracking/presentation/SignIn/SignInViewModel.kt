package com.example.devicetracking.presentation.SignIn


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
//import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.model.ChildLocationObject
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.repository.ChildRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



val PARENT = "Parent"
val CHILD = "Child"

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    //val childUsecases: ChildUsecases,
    //val parentUsecases: ParentUsecases,
):ViewModel() {

    var passwordWeak = false
    var passwordMedium = false
    var passwordStrong = false

    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var userType = mutableStateOf("")

    fun createUser(email:String){

        if (userType.value == CHILD){

            viewModelScope.launch {
                val child = ChildObject(email, firstName.value, lastName.value,
                     ChildLocationObject(), false)
                childRepository.createChild(child)

            }

        }

    }



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