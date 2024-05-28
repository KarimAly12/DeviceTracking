package com.example.devicetracking.features.parent.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.amplifyframework.ui.authenticator.ui.AuthenticatorLoading
import com.example.devicetracking.R
import com.example.devicetracking.core.barcode.BarcodeScanning
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ParentObject
import com.example.devicetracking.core.ui.navigation.Screens
import com.example.devicetracking.ui.theme.colorButton1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentScreen(parentViewModel: ParentViewModel = hiltViewModel(), navHostController: NavHostController){

    val context =  LocalContext.current
    val barcodeScanning by remember { mutableStateOf(BarcodeScanning(context)) }

    var tempEmail by remember {
        mutableStateOf("")
    }

    if(parentViewModel.parent.value == ParentObject()){
        
       AuthenticatorLoading()
        
    }else{

        Scaffold(
            floatingActionButton = {

                FloatingActionButton(
                    containerColor = colorButton1,
                    onClick = {
                        
                        barcodeScanning.starScan(
                            onScanSuccess = {

                            }
                        )

                    }
                ) {
                    Icon(painter = painterResource(id = R.drawable.round_qr_code_scanner_24),
                        contentDescription = "QR_CODE_SCANNER",
                        tint = Color.White
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {

            Column(
                modifier = Modifier.padding(it)
            ) {


                TextField(value = tempEmail, onValueChange = {
                    tempEmail = it
                })

                Button(onClick = {

                    parentViewModel.addChild(tempEmail)

                }) {
                    Text(text = "Add child")
                }


                //ChildLazyColumn(children = parentViewModel.children, navHostController)

            }
        }

    }



}


@Composable
fun ChildItem(child: ChildObject, navHostController: NavHostController){


    Row(
        modifier = Modifier
            .clickable {
                navHostController.navigate(Screens.ParentChildScreen.name + "/${child.email}")
            }
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                color = Color(0xFF367EDF),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp)

    ) {

        Text(
            text = child.firstName + " " + child.lastName,
            color = Color.White
        )


        Spacer(modifier = Modifier.weight(1f))


        if(child.inTrip){
            Image(painter = painterResource(id = R.drawable.walk),
                contentDescription = "Child in trip",
                modifier = Modifier.size(35.dp)
            )
        }

    }

}





@Composable
fun ChildLazyColumn(children:List<ChildObject>, navHostController: NavHostController){

    LazyColumn {
        items(children){
            ChildItem(child = it, navHostController)
        }
    }



}

