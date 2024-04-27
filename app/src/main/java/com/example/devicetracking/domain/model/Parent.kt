package com.example.devicetracking.domain.model

data class Parent(val firstName:String, val lastName:String, val email:String, val children:MutableList<String> = mutableListOf())