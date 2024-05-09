package com.example.devicetracking.core.location

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import java.util.concurrent.TimeUnit


object LocationUtils{

    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(3)
    ).build()


    const val LOCATION_ENABLE_REQUEST_CODE = 5234

    fun getCurrentLocationSettings(context: Context){

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val client:SettingsClient = LocationServices.getSettingsClient(context)
        val task:Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnFailureListener{exception ->
            if (exception is ResolvableApiException){

                try {


                    exception.startResolutionForResult(context as Activity, LOCATION_ENABLE_REQUEST_CODE)
                }catch (sendEx: IntentSender.SendIntentException){

                }

            }

        }


    }

}