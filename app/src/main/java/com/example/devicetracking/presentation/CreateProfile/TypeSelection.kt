package com.example.devicetracking.presentation.CreateProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.devicetracking.R
import com.example.devicetracking.presentation.Navigation.Screens
import com.example.devicetracking.ui.theme.colorButton1


@Composable
fun TypeSelection(
    navHostController: NavHostController
){

    var parentBoxSelected by remember { mutableStateOf(false) }
    var childBoxSelected by remember { mutableStateOf(false) }

    var selectedType by remember { mutableStateOf("") }

    Column(


    ){

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .height(170.dp)
        ){


            Box(

                modifier = Modifier

                    .clickable {
                        parentBoxSelected = true
                        childBoxSelected = false
                        selectedType = "parent"
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = Color(0xFFDDD8D8),
                        shape = RoundedCornerShape(8)
                    )
                    .border(
                        width = 3.dp,
                        brush = Brush.linearGradient(
                            colors = if (parentBoxSelected) listOf(colorButton1, colorButton1)
                            else listOf(Color.Transparent, Color.Transparent)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

            ) {
                Image(
                    painter = painterResource(id = R.drawable.parent),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center),

                )

            }

            Box(

                modifier = Modifier
                    .clickable {
                        parentBoxSelected = false
                        childBoxSelected = true
                        selectedType = "children"
                    }
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                    .background(
                        color = Color(0xFFDDD8D8),
                        shape = RoundedCornerShape(8)
                    )
                    .border(
                        width = 3.dp,
                        brush = Brush.linearGradient(
                            colors = if (childBoxSelected) listOf(colorButton1, colorButton1)
                            else listOf(Color.Transparent, Color.Transparent)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

            ) {
                Image(
                    painter = painterResource(id = R.drawable.child),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(60.dp),

                )

            }

        }

        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(colorButton1, colorButton1)
                    ),
                    shape = RoundedCornerShape(10)
                ),
            shape = RoundedCornerShape(10),
            onClick = {

                      navHostController.navigate(Screens.CreateProfile.name + "/$selectedType")

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Proceed",
                color = Color.White
            )

        }


        Spacer(modifier = Modifier.weight(1f))
    }
}


@Composable
@Preview
fun TypeSelectionPreview(){
}