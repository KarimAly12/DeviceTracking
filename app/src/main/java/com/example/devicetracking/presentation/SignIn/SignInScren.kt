package com.example.devicetracking.presentation.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.Parent
import com.example.devicetracking.presentation.Navigation.Screens
import com.example.devicetracking.ui.theme.colorButton1
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SingInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
     navHostController: NavHostController
){

    var passwordVisibility by remember { mutableStateOf(false) }

    val auth = Firebase.auth
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }




    if(signInViewModel.child.value.email != ""){
        navHostController.navigate(Screens.ChildHome.name){
            popUpTo(Screens.SignInScreen.name){

                inclusive = true
            }
        }
        signInViewModel.child.value = Child()

    }else if (signInViewModel.parent.value.email != ""){
        navHostController.navigate(Screens.ParentHome.name){
            popUpTo(Screens.SignInScreen.name){

                inclusive = true
            }
        }
        signInViewModel.parent.value = Parent()

    }

    Column {

        Spacer(modifier = Modifier.weight(1f))

        TextField(
            label = { Text("Email") },
            value = email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
               email = it
            } )

        TextField(
            label = {Text("Password")},
            value = password,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(
                    onClick = {passwordVisibility = !passwordVisibility}
                ){

                    Icon(imageVector = image, contentDescription = "Show Password")
                }
            },
            onValueChange ={
                password = it
                //signInViewModel.checkPasswordStrength()
            } )


        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorButton1, colorButton1)
                    ),
                    shape = RoundedCornerShape(10)
                ),
            shape = RoundedCornerShape(10),
            onClick = {

                signInViewModel.signIn(email,password, navHostController)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )

        }


        Button(
            modifier = Modifier
                .padding(end = 16.dp, start = 16.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorButton1, colorButton1)
                    ),
                    shape = RoundedCornerShape(10)
                ),
            shape = RoundedCornerShape(10),
            onClick = {

                navHostController.navigate(Screens.TypeSelection.name)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Create Profile",
                color = Color.White
            )

        }


        Spacer(modifier = Modifier.weight(1f))

    }
}