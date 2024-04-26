package com.example.devicetracking

import android.bluetooth.BluetoothAdapter
import android.net.wifi.WifiManager
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.util.UUID


@Composable
fun Barcode(){

    val uid = UUID.randomUUID().toString()
    val barcodeEncoder = BarcodeEncoder()
    val bitmap = barcodeEncoder.encodeBitmap(uid, BarcodeFormat.QR_CODE, 400, 400)



    Log.i("uid", uid)

    Column {

        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "barcode")

    }


}


fun getFID(){

    val uid = UUID.randomUUID().toString()
}

@Preview
@Composable
fun BarcodePreview(){
    Barcode()
}