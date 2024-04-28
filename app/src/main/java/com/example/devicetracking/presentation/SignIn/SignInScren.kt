package com.example.devicetracking.presentation.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.devicetracking.presentation.Navigation.Screens
import com.example.devicetracking.ui.theme.colorButton1
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SingInScreen(
     navHostController: NavHostController
){

    val auth = Firebase.auth
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    var p by remember {
        mutableStateOf("")
    }


    Column {

        TextField(
            label = { Text("Email") },
            value = p,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                p = it
            } )

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
            label = { Text("Password") },
            value = password,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                password = it
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


                      auth.signInWithEmailAndPassword(email, password)
                          .addOnCompleteListener{

                              if (it.isSuccessful) {

                                  if(p == "p"){
                                      navHostController.navigate(Screens.ParentHome.name)
                                  }else{
                                      navHostController.navigate(Screens.ChildHome.name)

                                  }

                              }


                          }



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



    }
}