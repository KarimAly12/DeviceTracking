package com.example.devicetracking.presentation.SignIn

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.amplifyframework.ui.authenticator.SignInState
import com.amplifyframework.ui.authenticator.SignUpState
import com.amplifyframework.ui.authenticator.forms.FieldKey
import com.amplifyframework.ui.authenticator.ui.Authenticator
import com.amplifyframework.ui.authenticator.ui.SignInFooter
import com.amplifyframework.ui.authenticator.ui.SignUpFooter
import com.example.devicetracking.presentation.Navigation.Screens
import com.example.flightdelivery.Presentation.CreateProfile.CreateUserScreen
import kotlinx.coroutines.launch

@Composable
fun SingInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
     navHostController: NavHostController
) {




    Column {
        Authenticator(

            signInContent = { state ->

                SignInScreen(signInViewModel = signInViewModel, state = state)


            },
            signUpContent = {state ->

                SignUpScreen(signInViewModel = signInViewModel, state = state)

                

            }

        ) { state ->


           if(state.user.username != ""){
               CreateUserScreen(navHostController = navHostController)
           }


        }

    }
}

@Composable
fun SignInScreen(signInViewModel: SignInViewModel, state: SignInState){

    var passwordVisibility by remember { mutableStateOf(false) }

    val email = state.form.fields[FieldKey.Email]
    val password = state.form.fields[FieldKey.Password]


    Column {




        OutlinedTextField(
            label = { Text("Email") },
            value = email!!.state.content,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {
                email.state.content = it
            })

        OutlinedTextField(
            label = { Text("Password") },
            value = password!!.state.content,
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            trailingIcon = {
                val image = if (passwordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility }
                ) {

                    Icon(imageVector = image, contentDescription = "Show Password")
                }
            },
            onValueChange = {
                password.state.content = it
                //signInViewModel.checkPasswordStrength()
            })


        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            onClick = {

                //signInViewModel.signIn(email,password, navHostController)
            },
        ) {
            Text(
                text = "Sign In",
                color = Color.White
            )

        }


        SignInFooter(state = state)



    }

}

@Composable
fun SignUpScreen(
    signInViewModel: SignInViewModel,
    state: SignUpState
){

    val email = state.form.fields[FieldKey.Email]
    val password = state.form.fields[FieldKey.Password]
    val confirmPassword = state.form.fields[FieldKey.ConfirmPassword]


    var passwordVisibility by remember { mutableStateOf(false) }
    var confirmPasswordVisibility by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()



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

        OutlinedTextField(
            label = {Text("Email")},
            value = email!!.state.content,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange ={
                email.state.content = it
            } )


        Column {

            OutlinedTextField(
                label = {Text("Password")},
                value = password!!.state.content,
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
                    password.state.content = it
                    signInViewModel.checkPasswordStrength(password.state.content)
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
                            color = if (signInViewModel.passwordWeak) Color(0xFFE55050) else Color(
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
                            color = if (signInViewModel.passwordMedium) Color(0xffEAAE3B) else Color(
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
                            color = if (signInViewModel.passwordStrong) Color(0xff82E81C) else Color(
                                0xFFE2DBDB
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {

                }

            }

        }

        OutlinedTextField(
            label = {Text("Confirm Password")},
            value = confirmPassword!!.state.content,
            visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            trailingIcon = {
                val image = if (confirmPasswordVisibility)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                IconButton(
                    onClick = {confirmPasswordVisibility = !confirmPasswordVisibility}
                ){

                    Icon(imageVector = image, contentDescription = "Show Password")
                }
            },
            onValueChange ={
                confirmPassword.state.content = it
            } )


        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            onClick = {

                scope.launch {
                    state.signUp()

                }

                //signInViewModel.signIn(email,password, navHostController)
            },
        ) {
            Text(
                text = "Sign Up",
                color = Color.White
            )

        }

        SignUpFooter(state = state)


    }

}