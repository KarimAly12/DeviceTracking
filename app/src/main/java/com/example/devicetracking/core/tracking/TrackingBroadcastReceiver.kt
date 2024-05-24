package com.example.devicetracking.core.tracking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.devicetracking.core.tracking.service.DefaultLocationServiceManager
import com.example.devicetracking.core.tracking.service.LocationService
import javax.inject.Inject


class TrackingBroadcastReceiver: BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        when(intent?.action){
            LocServiceEvents.StopLocService.event ->{
                val locServiceIntent = Intent(context, LocationService::class.java)
                val locServiceManager = DefaultLocationServiceManager(context!!,locServiceIntent)
                locServiceManager.stopService()

            }
        }
    }
}