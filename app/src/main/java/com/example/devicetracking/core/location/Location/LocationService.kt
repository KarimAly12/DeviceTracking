//package com.example.devicetracking.presentation.Location
//
//import android.Manifest
//import android.app.NotificationManager
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.content.pm.ServiceInfo
//import android.os.IBinder
//import android.util.Log
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import androidx.core.app.ServiceCompat
//import com.example.devicetracking.R
//import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
//import com.example.devicetracking.domain.Usecases.ParentUsecases.ParentUsecases
//import com.google.android.gms.location.LocationServices
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.SupervisorJob
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import java.lang.Exception
//import javax.inject.Inject
//
//
//@AndroidEntryPoint
//class LocationService: Service() {
//
//    private val serviceScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
//
//    private lateinit var locationProvider:LocationProvider
//
//    @Inject
//    lateinit var childUsecases: ChildUsecases
//
//    val authId = Firebase.auth.currentUser!!.uid
//
//    override fun onCreate() {
//        super.onCreate()
//        locationProvider = LocationProvider(this)
//        locationProvider.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//    }
//
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//
//
//
//        startForeground()
//
//        return super.onStartCommand(intent, flags, startId)
//    }
//    private fun startForeground(){
//
//        if(!areLocationPermissionGranted()){
//            stopSelf()
//            return
//        }
//
//        try {
//            val notification = NotificationCompat.Builder(this, "LOCATION_NOTIFY")
//                .setContentTitle("Location")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setOngoing(true)
//                .build()
//
//
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//
//            serviceScope.launch {
//
//                while (true){
//
//                    locationProvider.getCurrentLocation(
//                        onGetCurrentLocationSuccess = {location ->
//                            Log.i("test", location.toString())
//
//                            val updatedNotification = NotificationCompat.Builder(this@LocationService, "LOCATION_NOTIFY")
//                                .setContentTitle("Location")
//                                .setContentText(location.toString())
//                                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                                .build()
//
//                            notificationManager.notify(1, updatedNotification)
//
//                            serviceScope.launch {
//                                childUsecases.updatChildLocation(authId, location)
//                            }
//
//                        },
//                        onGetCurrentLocationFail = {}
//                    )
//
//                    delay(5000)
//                }
//            }
//
//
//            with(NotificationManagerCompat.from(this)){
//                if(ActivityCompat.checkSelfPermission(this@LocationService, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
//                    return@with
//                }
//
//                notify(1, notification)
//            }
//
//
//            startForeground(1, notification)
//
//
//
//
//
//        }catch (e:Exception){
//
//        }
//
//    }
//
//    override fun onBind(intent: Intent?): IBinder? {
//        TODO("Not yet implemented")
//    }
//
//
//    private fun areLocationPermissionGranted(): Boolean {
//
//        return (ActivityCompat.checkSelfPermission(
//            this, android.Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//            this, android.Manifest.permission.ACCESS_COARSE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//                )
//    }
//
//}