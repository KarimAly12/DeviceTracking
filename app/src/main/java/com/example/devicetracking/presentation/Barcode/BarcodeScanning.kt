package com.example.devicetracking.presentation.Barcode

import android.content.Context
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Timer

class BarcodeScanning(val context:Context) {

    val barCodeResults = MutableStateFlow<String?>(null)

    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE,
            Barcode.FORMAT_AZTEC
        )
        .enableAutoZoom()
        .build()

    fun starScan(){
        val scanner = GmsBarcodeScanning.getClient(context, options)

        scanner.startScan()
            .addOnSuccessListener{
                barCodeResults.value = it.rawValue


            }

    }

}