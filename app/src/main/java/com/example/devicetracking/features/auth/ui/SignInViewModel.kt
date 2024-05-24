package com.example.devicetracking.features.auth.ui


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
//import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.core.domain.model.ChildLocationObject
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ParentObject
import com.example.devicetracking.core.domain.repository.ChildRepository
import com.example.devicetracking.core.domain.repository.ParentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



val PARENT = "Parent"
val CHILD = "Child"

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val parentRepository: ParentRepository

):ViewModel() {

    var passwordWeak = false
    var passwordMedium = false
    var passwordStrong = false

    var userEmail =  mutableStateOf("")
    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var userType = mutableStateOf("")

    fun createUser(){

        if (userType.value == CHILD){

            childRepository.isChildExist(userEmail.value){found ->
                if(!found){
                    viewModelScope.launch {
                        val child = ChildObject(userEmail.value, firstName.value, lastName.value,
                            ChildLocationObject(), false)
                        childRepository.createChild(child)

                    }

                    Log.i("test", "in")
                }
            }

        }else if(userType.value == PARENT){

            parentRepository.isParentExist(userEmail.value){found ->

                if(!found){
                    viewModelScope.launch {
                        val parent = ParentObject(userEmail.value, firstName.value, lastName.value)
                        parentRepository.createParent(parent)

                    }

                    Log.i("test", "in")
                }

            }



        }
    }


    fun getUserType(){

        try{

            Amplify.Auth.fetchUserAttributes(
                {

                    childRepository.isChildExist(email = it[0].value.toString()){exist ->
                        if(exist){
                            userType.value = CHILD

                        }
                    }

                    parentRepository.isParentExist(email = it[0].value.toString()){exist ->
                        if(exist){
                            userType.value = PARENT
                        }
                    }
                },
                {}
            )

        }catch (e: Exception){
            Log.e("SIGN_IN_VIEW_MODEL", e.message.toString())
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