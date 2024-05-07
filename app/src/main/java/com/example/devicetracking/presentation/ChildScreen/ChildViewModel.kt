package com.example.devicetracking.presentation.ChildScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.model.Child
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChildViewModel @Inject constructor(
    childUsecases: ChildUsecases
): ViewModel(){


    val childId = Firebase.auth.currentUser!!.uid
    private val barcodeEncoder = BarcodeEncoder()
    val bitmap = barcodeEncoder.encodeBitmap(childId, BarcodeFormat.QR_CODE, 400, 400)
    val child:MutableState<Child> = mutableStateOf(Child())

    init {

        viewModelScope.launch {

            childUsecases.getChild(childId, child)
        }
    }


}