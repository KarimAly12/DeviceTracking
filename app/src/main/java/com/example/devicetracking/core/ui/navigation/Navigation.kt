package com.example.devicetracking.core.ui.navigation

import com.example.devicetracking.features.auth.ui.SingInScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.devicetracking.presentation.ChildParentScreen.ChildParentScreen
import com.example.devicetracking.features.parent.ui.ParentScreen


@Composable
fun Navigation(){

    val navHostController = rememberNavController()
    NavHost(navController = navHostController , startDestination = Screens.SignInScreen.name){

        composable(
            Screens.TypeSelection.name,

            ){

        }
//        composable(
//            Screens.CreateUserScreen.name
//        ){
//            CreateUserScreen(navHostController = navHostController)
//        }

//        composable(Screens.ChildHome.name){
//            ChildScreen()
//        }
        composable(Screens.ParentHome.name){
            ParentScreen(navHostController = navHostController)
        }

        composable(Screens.SignInScreen.name){
            SingInScreen(navHostController= navHostController)
        }

        composable(
            Screens.ParentChildScreen.name +"/{childID}",
            arguments = listOf(navArgument("childID"){type = NavType.StringType})
        ){
            ChildParentScreen()

        }

    }
}