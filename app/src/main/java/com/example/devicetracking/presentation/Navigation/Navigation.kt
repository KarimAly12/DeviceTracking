package com.example.devicetracking.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.devicetracking.presentation.Child.ChildHome
import com.example.devicetracking.presentation.CreateProfile.TypeSelection
import com.example.devicetracking.presentation.Parent.ParentHome
import com.example.devicetracking.presentation.SignIn.SingInScreen
import com.example.flightdelivery.Presentation.CreateProfile.CreateProfileScreen


@Composable
fun Navigation(){

    val navHostController = rememberNavController()
    NavHost(navController = navHostController , startDestination = Screens.SignInScreen.name){

        composable(
            Screens.TypeSelection.name,

        ){
            TypeSelection(
                navHostController = navHostController
            )
        }
        composable(
            Screens.CreateProfile.name +"/{type}",
            arguments =  listOf(navArgument("type") {type = NavType.StringType})
        ){
            CreateProfileScreen(navHostController = navHostController)
        }

        composable(Screens.ChildHome.name){
            ChildHome()
        }
        composable(Screens.ParentHome.name){
            ParentHome()
        }

        composable(Screens.SignInScreen.name){
            SingInScreen(navHostController= navHostController)
        }

    }
}