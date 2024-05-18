package com.example.flightdelivery.Presentation.CreateProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.devicetracking.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUserScreen(cpViewModel: CreateUserViewModel = hiltViewModel(), navHostController: NavHostController){



    Column {

        Spacer(modifier = Modifier.weight(1f))

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
            value = cpViewModel.firstName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {
                cpViewModel.firstName.value = it
            }
        )

        OutlinedTextField(
            label = { Text("Last Name") },
            value = cpViewModel.lastName.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onValueChange = {
                cpViewModel.lastName.value = it
            }
        )


        UserTypeSelection(cpViewModel)

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
                text = "Create User",
                color = Color.White
            )

        }

        Spacer(modifier = Modifier.weight(1f))


    }

}


@Composable
fun UserTypeSelection(
    cpViewModel: CreateUserViewModel
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
                        cpViewModel.userType.value = "parent"
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = if (parentBoxSelected) Color(0xFF295C92) else Color.Transparent,
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

                    Text(text = "Parent")


                }

            }

            Box(

                modifier = Modifier
                    .clickable {
                        parentBoxSelected = false
                        childBoxSelected = true
                        cpViewModel.userType.value
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = if (childBoxSelected) Color(0xFF295C92) else Color.Transparent,
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

                    Text(text = "Child")


                }

            }

        }




    }
}


@Composable
fun VerificationCodeDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: (String) -> Unit,
){


    var verificationCode by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismissRequest()},
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmRequest(verificationCode)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }

        },
        title = { Text(
            text = "Verification Code",
            textAlign = TextAlign.Center
        ) },
        text = {

            Column {
                Text(text = "Enter the verification code sent to your email")

                TextField(value = verificationCode,
                    onValueChange = {
                        verificationCode = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )


            }
        }
    )



}

@Preview
@Composable
fun CreateProfilePreview(){
}