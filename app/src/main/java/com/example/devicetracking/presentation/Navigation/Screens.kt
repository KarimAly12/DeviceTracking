package com.example.devicetracking.presentation.Navigation

sealed class Screens(val name:String){
    object SignInScreen :Screens("signInScreen")
    object ParentHome:Screens("parenthome")

    object TypeSelection:Screens("typeselection")
    object CreateUserScreen:Screens("createUserScreen")
    object ChildHome:Screens("childHome")
    object ParentChildScreen:Screens("parentchildscreen")
}
