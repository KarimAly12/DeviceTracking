package com.example.devicetracking

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DeviceTrackingApp:Application() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()


    }

    private fun createNotificationChannel(){

        val channel = NotificationChannel(
            "LOCATION_NOTIFY", "location", NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager :NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}