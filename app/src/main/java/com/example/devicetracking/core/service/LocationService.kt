package com.example.devicetracking.presentation.Location

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.devicetracking.core.location.LocationManager
import com.example.devicetracking.domain.model.ChildLocationObject
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationService: Service() {


    private lateinit var locationManager: LocationManager

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    //@Inject
    //lateinit var childUsecases: ChildUsecases


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
                    val childLocation = ChildLocationObject(location.latitude, location.longitude)
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