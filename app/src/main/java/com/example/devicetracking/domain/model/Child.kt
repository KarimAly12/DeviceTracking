package com.example.devicetracking.domain.model

data class Child(val firstName:String, val lastName:String, val email:String, val location:Pair<Float, Float>, val inTrip:Boolean = false)