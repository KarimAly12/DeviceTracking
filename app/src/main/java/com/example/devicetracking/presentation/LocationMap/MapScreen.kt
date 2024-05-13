package com.example.devicetracking.presentation.LocationMap

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.devicetracking.core.location.LocationManager
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState



@Composable
fun LocationMap(
    modifier: Modifier = Modifier,
    locationLatLng:LatLng,
    cameraState: CameraPositionState){


    Log.i("locationLatLng", locationLatLng.toString())



    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.TERRAIN))
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraState,
        properties = properties

    ){
        Marker(
            state = MarkerState(position = locationLatLng)
        )
    }

}




@Composable
fun LocationUpdatesEffect(
    locationRequest: LocationRequest,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onUpdate: (result: LocationResult) -> Unit
){

    val context = LocalContext.current
    val currentOnUpdate by rememberUpdatedState(onUpdate)

    DisposableEffect(locationRequest, lifecycleOwner) {
        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        val locationCallback: LocationCallback = object : LocationCallback(){
            override fun onLocationResult(result: LocationResult) {
                currentOnUpdate(result)
            }
        }

        val locationManager = LocationManager(
            context,
            locationClient,
            locationRequest
        )

            val observer = LifecycleEventObserver{ _, event ->
            if(event == Lifecycle.Event.ON_START){
                locationManager.registerCallback(locationCallback)
            }else if(event == Lifecycle.Event.ON_STOP){
                locationManager.unRegisterCallback(locationCallback)
            }

        }


        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {

            locationManager.unRegisterCallback(
                locationCallback
            )
            lifecycleOwner.lifecycle.removeObserver(observer)

        }
        
    }

}



suspend fun CameraPositionState.centerOnLocation(location: LatLng, zoom:Float){

    animate(
        update = CameraUpdateFactory.newLatLngZoom(
            location, zoom
        ),
        durationMs = 1500
    )
}



@Preview
@Composable
fun GoogleMapScreenPreview(){
    }