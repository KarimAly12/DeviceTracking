package com.example.devicetracking.presentation.ChildScreen

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.example.devicetracking.domain.model.ChildObject
import com.example.devicetracking.domain.repository.ChildRepository
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChildViewModel @Inject constructor(
    private val childRepository: ChildRepository
): ViewModel(){


    var email:String? = null
    private val barcodeEncoder = BarcodeEncoder()
    lateinit var bitmap:Bitmap
    val child:MutableState<ChildObject> = mutableStateOf(ChildObject())


    init {
        viewModelScope.launch {
            initChild()
        }
    }



    fun updateChild(childObject: ChildObject){
        viewModelScope.launch {
            childRepository.updateChild(childObject){updatedChild->
                child.value = updatedChild
            }
        }
    }

    private fun initChild(){
        Amplify.Auth.fetchUserAttributes(
            {
                email = it[0].value
                bitmap = barcodeEncoder.encodeBitmap(email, BarcodeFormat.QR_CODE, 400, 400)
                childRepository.getChild(
                    email!!
                ){ child.value = it }


            },
            {}
        )
    }




}