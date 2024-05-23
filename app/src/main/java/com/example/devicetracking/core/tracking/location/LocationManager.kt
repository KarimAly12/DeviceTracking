package com.example.devicetracking.core.tracking.location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import dagger.hilt.android.qualifiers.ApplicationContext

class LocationManager(
    @ApplicationContext private val context: Context,
    var fusedLocationClient: FusedLocationProviderClient,
    var locationRequest:LocationRequest
) {




      @SuppressLint("MissingPermission")
      fun registerCallback(locationCallback: LocationCallback){

          if (areLocationPermissionGranted()){

              fusedLocationClient.requestLocationUpdates(
                  locationRequest,
                  locationCallback,
                  Looper.getMainLooper()

              )

          }


      }


    fun unRegisterCallback(locationCallback: LocationCallback){
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

//    @SuppressLint("MissingPermission")
//    fun getCurrentLocation(
//         onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
//         onGetCurrentLocationFail: (Exception) -> Unit,
//         priority: Boolean = true
//     ){
//
//         val accuracy = if(priority) Priority.PRIORITY_HIGH_ACCURACY
//         else Priority.PRIORITY_BALANCED_POWER_ACCURACY
//
//         if(areLocationPermissionGranted()){
//
//             fusedLocationClient
//                 .getCurrentLocation(accuracy, CancellationTokenSource().token)
//                 .addOnSuccessListener{location ->
//
//                     onGetCurrentLocationSuccess(Pair(location.latitude, location.longitude))
//                 }
//                 .addOnFailureListener{
//                     onGetCurrentLocationFail(it)
//                 }
//
//         }
//
//
//     }


    private fun areLocationPermissionGranted(): Boolean {

        return (ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                )
    }


}
