package com.example.devicetracking.core.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun ScreenNavigationDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    content: @Composable () -> Unit,

    ){


    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {

                drawerContent()

                Spacer(modifier = Modifier.weight(1f))

            }



    }) {

        content()
    }
}

fun signOut(){

    Firebase.auth.signOut()
}