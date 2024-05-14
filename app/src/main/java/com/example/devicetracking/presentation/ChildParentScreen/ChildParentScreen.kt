package com.example.devicetracking.presentation.ChildParentScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.devicetracking.presentation.LocationMap.LocationMap
import com.example.devicetracking.presentation.LocationMap.centerOnLocation
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun ChildParentScreen(
    childParentViewModel: ChildParentViewModel = hiltViewModel()
){


    ChildParentMap(childParentViewModel = childParentViewModel)


}

@Composable
fun ChildParentMap(
    childParentViewModel: ChildParentViewModel
){
    var locationLatLng by remember { mutableStateOf(LatLng(0.0,0.0)) }
    val cameraState = rememberCameraPositionState()



    childParentViewModel.apply {
        LaunchedEffect(key1 = childLocation.value){
            Log.i("testchildparentscreen" , "inLaunchedEffect")
            locationLatLng = LatLng(childLocation.value.latitude, childLocation.value.longitude)
            cameraState.centerOnLocation(locationLatLng, cameraState.position.zoom)

        }

    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {



        LocationMap(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .size(600.dp),
            locationLatLng = locationLatLng,
            cameraState)
    }



}
