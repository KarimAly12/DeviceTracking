package com.example.devicetracking.presentation.Parent

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.devicetracking.R
import com.example.devicetracking.presentation.Barcode.BarcodeScanning
import com.example.devicetracking.ui.theme.colorButton1
import com.example.devicetracking.ui.theme.colorButton2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParentHome(parentViewModel: ParentViewModel = hiltViewModel()){

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
           
           LazyColumn {
               items(parentViewModel.children){
                   Text(text = it.email)
               }
           }
           
       } 
        
    }



}

