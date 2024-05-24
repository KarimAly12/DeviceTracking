package com.example.devicetracking.core.ui.navigation

sealed class Screens(val name:String){
    object SignInScreen : Screens("signInScreen")
    object ParentHome: Screens("parenthome")

    object TypeSelection: Screens("typeselection")
    object CreateUserScreen: Screens("createUserScreen")
    object ChildHome: Screens("childHome")
    object ParentChildScreen: Screens("parentchildscreen")
}
