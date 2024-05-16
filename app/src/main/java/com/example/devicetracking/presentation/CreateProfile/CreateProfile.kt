package com.example.flightdelivery.Presentation.CreateProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.devicetracking.presentation.Navigation.Screens
import com.example.devicetracking.ui.theme.colorButton1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProfileScreen(cpViewModel: CreateProfileViewModel = hiltViewModel(), navHostController: NavHostController){




    var passwordVisibility by remember { mutableStateOf(false) }


    if(cpViewModel.isCreateSuccess.value){

        if (cpViewModel.type == "children"){
            navHostController.navigate(Screens.ChildHome.name)
            cpViewModel.isCreateSuccess.value =false

        }else{
            navHostController.navigate(Screens.ParentHome.name)
            cpViewModel.isCreateSuccess.value =false
        }

    }


    Column {


        Row(
            modifier = Modifier.padding(32.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))


        }

        TextField(
            label = {Text("First Name")},
            value = cpViewModel.firstName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                cpViewModel.firstName.value = it
            } )

        TextField(
            label = {Text("Last Name")},
            value = cpViewModel.lastName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                cpViewModel.lastName.value = it
            } )

        TextField(
            label = {Text("Username")},
            value = cpViewModel.username.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                cpViewModel.username.value = it
            } )

        TextField(
            label = {Text("Email")},
            value = cpViewModel.email.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                cpViewModel.email.value = it
            } )


        Column {


            TextField(
                label = {Text("Password")},
                value = cpViewModel.password.value,
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
                    cpViewModel.password.value = it
                    cpViewModel.checkPasswordStrength()
                } )

            Row(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxWidth()
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ){
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (cpViewModel.passwordWeak) Color(0xFFE55050) else Color(
                                0xFFE2DBDB
                            ),
                            shape = RoundedCornerShape(5.dp)

                        )
                ) {

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (cpViewModel.passwordMedium) Color(0xffEAAE3B) else Color(
                                0xFFE2DBDB
                            ),
                            shape = RoundedCornerShape(5.dp)

                        )
                ) {

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight()
                        .background(
                            color = if (cpViewModel.passwordStrong) Color(0xff82E81C) else Color(
                                0xFFE2DBDB
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {

                }

            }

        }

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

                cpViewModel.signUp()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Create Account",
                color = Color.White
            )

        }

//        if(!cpViewModel.signinedUp.value){
//
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .padding(16.dp),
//            )
//
//
//        }


    }


}

@Preview
@Composable
fun CreateProfilePreview(){
}