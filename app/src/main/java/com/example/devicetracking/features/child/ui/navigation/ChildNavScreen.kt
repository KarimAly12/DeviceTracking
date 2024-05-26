package com.example.devicetracking.features.child.ui.navigation

import androidx.annotation.DrawableRes
import com.example.devicetracking.R

sealed class ChildNavScreen(
    val route:String, @DrawableRes val resourceId: Int, val name:String
) {

    object Home: ChildNavScreen(route = "home", resourceId = R.drawable.round_home, name = "Home")
    object Tracking: ChildNavScreen(route = "tracking", resourceId = R.drawable.round_map, name="Tracking")
    object Profile: ChildNavScreen(route = "profile", resourceId = R.drawable.round_account_circle, name="Profile")

}