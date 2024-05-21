package com.example.devicetracking.presentation.SignIn


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.api.graphql.GraphQLResponse
import com.amplifyframework.api.graphql.model.ModelQuery
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Child
import com.amplifyframework.datastore.generated.model.Parent
import com.example.devicetracking.R
//import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.model.ChildLocationObject
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.android.gms.common.api.ApiException
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
        }
    }


    fun getUserType(){

        try{

            Amplify.Auth.fetchUserAttributes(
                {
                    Log.i("email", it[0].value.toString())
                    childRepository.isChildExist(email = it[0].value.toString()){
                        userType.value = CHILD
                    }

                    isParentExist(email = it[0].value.toString()){
                        userType.value = PARENT
                    }
                },
                {}
            )

        }catch (e: Exception){
            Log.e("SIGN_IN_VIEW_MODEL", e.message.toString())
        }

    }




    private fun isParentExist(email:String, onParentFound: (Boolean) -> Unit) {

        try {
            Amplify.API.query(
                ModelQuery.get(Parent::class.java, email),
                {parent ->
                    if(parent.data != null){
                        onParentFound(true)

                    }else{
                        onParentFound(false)
                    }

                },
                {

                }
            )
        } catch (error: ApiException) {


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