package com.example.devicetracking.presentation.Navigation

sealed class Screens(val name:String){
    object TypeSelection:Screens("typeselection")
    object CreateProfile:Screens("createprofile")
    object ChildHome:Screens("childHome")
}
