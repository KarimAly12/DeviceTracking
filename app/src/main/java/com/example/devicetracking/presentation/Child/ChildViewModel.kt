package com.example.devicetracking.presentation.Child

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChildViewModel @Inject constructor(): ViewModel(){


    val childId = Firebase.auth.currentUser!!.uid
    private val barcodeEncoder = BarcodeEncoder()
    val bitmap = barcodeEncoder.encodeBitmap(childId, BarcodeFormat.QR_CODE, 400, 400)


}