package com.example.devicetracking.presentation.SignIn

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.presentation.Navigation.Screens
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val childUsecases: ChildUsecases,
    val parentUsecases: ParentUsecases,
):ViewModel() {


    val auth = Firebase.auth
    val child:MutableState<Child> = mutableStateOf(Child())
    val parent:MutableState<Parent> = mutableStateOf(Parent())


    fun signIn(email:String, password:String, navHostController: NavHostController) {



        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{

                try{

                    if (it.isSuccessful) {
                        val user = auth.currentUser

                        viewModelScope.launch {
                            childUsecases.getChild(user?.uid!!, child)
                            parentUsecases.getParent(user.uid, parent)

//                        if(child.value.email != ""){
//                            navHostController.navigate(Screens.ChildHome.name)
//                        }else if (parent.value.email != ""){
//                            navHostController.navigate(Screens.ParentHome.name)
//
//                        }

                        }





                    } else {
                        Log.i("test", it.result.toString())

                    }



                }catch (e:Exception){
                    Log.i("signinexception", e.toString())
                }



            }



    }
}