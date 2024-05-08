//package com.example.devicetracking
//
//import android.content.Context
//import android.util.Log
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.example.devicetracking.presentation.Location.LocationManager
//import com.google.android.gms.location.LocationServices
//
//class UploadLocationWorker(appContext: Context, workerParameters: WorkerParameters):Worker(appContext, workerParameters) {
//
//    val locationProvider = LocationManager(context = appContext)
//    init {
//        locationProvider.fusedLocationClient = LocationServices.getFusedLocationProviderClient(appContext)
//    }
//    override fun doWork(): Result {
//        while (true){
//
//            locationProvider.getCurrentLocation(
//                onGetCurrentLocationSuccess = {
//                    Log.i("test", it.toString())
//                },
//                onGetCurrentLocationFail = {}
//            )
//
//
//        }
//    }
//
//}