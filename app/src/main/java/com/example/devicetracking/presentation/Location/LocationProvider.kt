package com.example.devicetracking.presentation.Location

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.lang.Exception

class LocationProvider(
    private val context: Context
) {

      var fusedLocationClient:FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)



    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
         onGetCurrentLocationSuccess: (Pair<Double, Double>) -> Unit,
         onGetCurrentLocationFail: (Exception) -> Unit,
         priority: Boolean = true
     ){

         val accuracy = if(priority) Priority.PRIORITY_HIGH_ACCURACY
         else Priority.PRIORITY_BALANCED_POWER_ACCURACY

         if(areLocationPermissionGranted()){

             fusedLocationClient
                 .getCurrentLocation(accuracy, CancellationTokenSource().token)
                 .addOnSuccessListener{location ->

                     onGetCurrentLocationSuccess(Pair(location.latitude, location.longitude))
                 }
                 .addOnFailureListener{
                     onGetCurrentLocationFail(it)
                 }
                 
         }


     }

    private fun areLocationPermissionGranted(): Boolean {

        return (ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                )
    }


}
