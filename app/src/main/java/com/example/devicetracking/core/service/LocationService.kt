package com.example.devicetracking.presentation.Location

import android.Manifest
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import com.example.devicetracking.R
import com.example.devicetracking.core.location.LocationManager
import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
import com.example.devicetracking.domain.model.ChildLocation
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@AndroidEntryPoint
class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)

    private lateinit var locationManager: LocationManager

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    //@Inject
    //lateinit var childUsecases: ChildUsecases

    val authId = Firebase.auth.currentUser!!.uid

    private lateinit var locationCallback:LocationCallback
    private var locationRequest: LocationRequest? = null

    override fun onCreate() {
        super.onCreate()

        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000).build()
        locationManager = LocationManager(this, fusedLocationProviderClient, locationRequest!!)


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(restult: LocationResult) {
                for (location in restult.locations) {
                    val childLocation = ChildLocation(location.latitude, location.longitude)
                    scope.launch {
                        //childUsecases.updatChildLocation(authId, childLocation)
                        //Log.i("testLocationService", latLng.toString())

                    }
                }
            }
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        locationManager.registerCallback(locationCallback)

        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        super.onDestroy()
        locationRequest = null
        locationManager.unRegisterCallback(locationCallback)
        job.cancel()
    }



//    private fun areLocationPermissionGranted(): Boolean {
//
//        return (ActivityCompat.checkSelfPermission(
//            this, android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//            this, android.Manifest.permission.ACCESS_COARSE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//                )
//    }

}