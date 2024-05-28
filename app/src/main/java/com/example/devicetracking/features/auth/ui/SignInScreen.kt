package com.example.devicetracking.features.auth.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
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
import com.example.devicetracking.R
import com.example.devicetracking.features.child.ui.ChildScreen
import com.example.devicetracking.features.child.ui.navigation.ChildNavScreen
import com.example.devicetracking.features.parent.ui.ParentScreen
import kotlinx.coroutines.launch

@Composable
fun SingInScreen(
    signInViewModel: SignInViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val scope = rememberCoroutineScope()

    Column {
        Authenticator(

            signInContent = { state ->
                SignInScreen(signInViewModel = signInViewModel, state = state)
            },
            signUpContent = {state ->
                SignUpScreen(signInViewModel = signInViewModel, state = state)
            },
            errorContent = {state ->
                Log.i("login_error", state.error.message.toString())

            }

        ) { state ->

            Log.i("test", signInViewModel.userType.value)


            if(signInViewModel.userType.value == CHILD){

                signInViewModel.createUser()
                ChildScreen()
            }else if(signInViewModel.userType.value == PARENT){
                signInViewModel.createUser()
                ParentScreen(navHostController = navHostController)
            }else{

                signInViewModel.getUserType()
            }

        }

    }
}

@Composable
fun SignInScreen(signInViewModel: SignInViewModel, state: SignInState){

    val scope = rememberCoroutineScope()

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
                signInViewModel.userEmail.value = it
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

                scope.launch {

                    state.signIn()
                }

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





    val email = state.form.fields[FieldKey.Email]!!
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
                "Create User",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }



        OutlinedTextField(
            label = { Text("First Name") },
            value = signInViewModel.firstName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {
                signInViewModel.firstName.value = it
            }
        )

        OutlinedTextField(
            label = { Text("Last Name") },
            value = signInViewModel.lastName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {
                signInViewModel.lastName.value = it
            }
        )


        UserTypeSelection(signInViewModel)




        Column {

            OutlinedTextField(
                label = {Text("Email")},
                value = email.state.content,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                onValueChange ={
                    email.state.content = it
                }
            )


            OutlinedTextField(
                label = {Text("Password")},
                value = password!!.state.content,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
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
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
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
                .padding(start = 16.dp, end = 16.dp),
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
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            onClick = {


                scope.launch {
                    signInViewModel.userEmail.value = email.state.content
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


@Composable
fun UserTypeSelection(
    signInViewModel: SignInViewModel
){

    var parentBoxSelected by remember { mutableStateOf(false) }
    var childBoxSelected by remember { mutableStateOf(false) }

    //var selectedType by remember { mutableStateOf("") }

    Column(


    ){

        Row(
            modifier = Modifier
                .height(170.dp)
        ){


            Box(

                modifier = Modifier

                    .clickable {
                        parentBoxSelected = true
                        childBoxSelected = false
                        signInViewModel.userType.value = PARENT
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = if (parentBoxSelected) Color(0xFF64A5E9) else Color.Transparent,
                        shape = RoundedCornerShape(8)
                    )


            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {

                    Image(
                        painter = painterResource(id = R.drawable.parent),
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp),
                    )

                    Text(text = PARENT)


                }

            }

            Box(

                modifier = Modifier
                    .clickable {
                        parentBoxSelected = false
                        childBoxSelected = true
                        signInViewModel.userType.value = CHILD
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = if (childBoxSelected) Color(0xFF64A5E9) else Color.Transparent,
                        shape = RoundedCornerShape(8)
                    )


            ) {

                Column(modifier = Modifier.align(Alignment.Center)) {

                    Image(
                        painter = painterResource(id = R.drawable.child),
                        contentDescription = "",
                        modifier = Modifier
                            .size(60.dp),
                    )

                    Text(text = CHILD)


                }

            }

        }
    }
}