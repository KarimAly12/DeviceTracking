package com.example.devicetracking.presentation.NavigationDrawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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