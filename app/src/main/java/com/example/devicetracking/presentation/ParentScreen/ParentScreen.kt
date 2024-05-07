package com.example.devicetracking.presentation.ParentScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.devicetracking.R
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.ui.theme.colorButton1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentScreen(parentViewModel: ParentViewModel = hiltViewModel()){


    if(parentViewModel.children.isEmpty()){
        parentViewModel.getChildren()
    }

    val context =  LocalContext.current
    val barcodeScanning by remember { mutableStateOf(BarcodeScanning(context)) }
    Scaffold(
        floatingActionButton = {
            
            FloatingActionButton(
                containerColor = colorButton1,
                onClick = {

                    Log.i("test", "parent home")

                    barcodeScanning.starScan(
                        onScanSuccess = {
                            parentViewModel.addChild(it)
                            parentViewModel.getChildren()
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
           
           ChildLazyColumn(children = parentViewModel.children)
           
       } 
        
    }



}


@Composable
fun ChildItem(child:Child){


    Row(
        modifier = Modifier
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
fun ChildLazyColumn(children:List<Child>){

    LazyColumn {
        items(children){
            ChildItem(child = it)
        }
    }



}

