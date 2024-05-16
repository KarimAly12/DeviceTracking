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
import com.google.android.gms.auth.api.Auth

class UserAuthRepositoryImpl:UserAuthRepository {
    override  fun signUp(
        username: String,
        email:String,
        password:String,
        code:String
    ) {

        val attrs = mapOf(
            AuthUserAttributeKey.email() to email,

        )

        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), email)
            .build()




        try{

            Amplify.Auth.signUp(username, password, options,
                {result->

                    when(result.nextStep.signUpStep){
                        AuthSignUpStep.DONE ->{
                            Log.i("AuthQuickstart", "Sign up OK: result")

                        }
                        else->{}
                    }


                },
                {error->

                })


            //Log.i("AuthQuickstart", "Sign up OK: result")

        }catch(e:AuthException){
            Log.e("AuthQuickstart", "Sign up failed", e)
        }

    }


}