package com.example.devicetracking.core.tracking.notification

import android.app.Notification
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

class LocationNotificationHelper(
    @ApplicationContext private val context: Context
):LocationNotification {

   override var notificationBuilder: Notification.Builder = Notification
       .Builder(context, CHANNEL_ID)
       .setContentTitle("Tracking")
       .setContentText("Child in trip")


    override fun createNotificationChannel() {
        
    }

    override fun updateLocationNotification() {
        TODO("Not yet implemented")
    }

    override fun removeLocationNotification() {
        TODO("Not yet implemented")
    }


    companion object {
        const val CHANNEL_ID = "LOCATION_NOTIFY_CHANNEL"
        const val CHANNEL_NAME = "Location Channel"
    }

}

