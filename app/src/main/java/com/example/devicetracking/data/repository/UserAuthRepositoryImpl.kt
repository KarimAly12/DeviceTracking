package com.example.devicetracking.data.repository

import android.util.Log
import androidx.work.Operation.State.SUCCESS
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthUserAttribute
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.auth.result.AuthSignUpResult
import com.amplifyframework.auth.result.step.AuthSignUpStep
import com.amplifyframework.core.Amplify
import com.example.devicetracking.domain.repository.UserAuthRepository
import com.example.devicetracking.presentation.Navigation.Screens
import com.google.android.gms.auth.api.Auth

class UserAuthRepositoryImpl:UserAuthRepository {


    override  fun signUp(
        email:String,
        password:String,
        onSignUpSuccess:()->Unit,
        onSignUpFailure:(String)->Unit
    ) {



        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()




        try{

            Amplify.Auth.signUp(email, password, options,
                {result->

                    when(result.nextStep.signUpStep){
                        AuthSignUpStep.DONE ->{
                            Log.i("AuthQuickstart", "Sign up OK: result")

                        }
                        AuthSignUpStep.CONFIRM_SIGN_UP_STEP ->{
                            onSignUpSuccess()
                        }
                        else->{}
                    }


                },
                {error->

                   onSignUpFailure(error.toString())

                })



        }catch(e:AuthException){
            Log.e("AuthQuickstart", "Sign up failed", e)
        }

    }


     override fun confirmSignUp(
        email:String,
        code:String,
        onConfirmEmailSuccess:()->Unit,
        onConfirmEmailFailure:(String)->Unit
    ){
        Amplify.Auth.confirmSignUp(
            email,
            code,
            {result->
                when(result.nextStep.signUpStep){
                    AuthSignUpStep.DONE ->{
                        Log.i("AuthQuickstart", "Confirm sign up OK")
                       onConfirmEmailSuccess()
                    }
                    AuthSignUpStep.CONFIRM_SIGN_UP_STEP ->{
                        onConfirmEmailFailure("Verification code does not match")
                    }
                    else -> {}
                }
            },
            {error->
                onConfirmEmailFailure(error.toString())
            }
        )
    }

}