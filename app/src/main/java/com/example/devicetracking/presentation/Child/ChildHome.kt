package com.example.devicetracking.presentation.Child

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder


@Composable
fun ChildHome(childViewModel:ChildViewModel = hiltViewModel()){


    Column{
        Log.i("test", "1t")

        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier
                .size(400.dp)
                .padding(8.dp),
            bitmap = childViewModel.bitmap.asImageBitmap(),
            contentDescription = "")
        
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            text = "Scan to add child device",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.weight(1f))


    }


}