package com.example.devicetracking.features.child.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.devicetracking.features.child.ui.ChildLocationMap
import com.example.devicetracking.features.child.ui.ChildHome
import com.example.devicetracking.features.child.ui.ChildViewModel


@Composable
fun ChildNavigation(
    modifier: Modifier = Modifier,
    childViewModel: ChildViewModel,
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = ChildNavScreen.Home.route ) {
        composable(ChildNavScreen.Home.route){
            ChildHome(childViewModel = childViewModel, navHostController = navHostController)
        }
        composable(ChildNavScreen.Tracking.route){
            ChildLocationMap(childViewModel = childViewModel)
        }

        composable(ChildNavScreen.Profile.route){

        }
        
    }
}