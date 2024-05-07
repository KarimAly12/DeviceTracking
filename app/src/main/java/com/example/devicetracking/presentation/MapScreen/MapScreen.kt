package com.example.devicetracking.presentation.MapScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState


@Composable
fun GoogleMapScreen(){

    val location = LatLng(40.7128, 74.0060)

    val cameraPosition = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(location, 5f)
    }

    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPosition,
        properties = properties
    ){
        Marker(
            state = MarkerState(position = location),

        )
    }

}

@Preview
@Composable
fun GoogleMapScreenPreview(){
    GoogleMapScreen()
}