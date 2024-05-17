package com.example.devicetracking.domain.repository

import com.amplifyframework.auth.result.AuthSignUpResult

interface UserAuthRepository {

     fun signUp(email:String,
                password:String,
                onSignUpSuccess:()->Unit,
                onSignUpFailure:(String)->Unit
     )

     fun confirmSignUp(email:String, code:String, onConfirmEmailSuccess:()->Unit, onConfirmEmailFailure:(String)->Unit)

}