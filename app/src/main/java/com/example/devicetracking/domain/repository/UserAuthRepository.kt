package com.example.devicetracking.domain.repository

interface UserAuthRepository {

     fun signUp(username:String, email:String, password:String, code:String)
}