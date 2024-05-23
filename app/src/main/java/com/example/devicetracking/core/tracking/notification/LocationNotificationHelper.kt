package com.example.devicetracking.core.tracking.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.example.devicetracking.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
):LocationNotification {

   override var notificationBuilder: Notification.Builder = Notification
       .Builder(context, CHANNEL_NAME_ID)
       .setContentTitle("Tracking")
       .setContentText("Child in trip")
       .setSmallIcon(R.drawable.walk)

   private val notificationManager:NotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager



    override fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_NAME_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)

    }

    override fun showLocationNotification() {
        with(NotificationManagerCompat.from(context)){
            if(ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED ){

                // Do something here is not permission is not presented

                return@with
            }

            notify(NOTIFICATION_ID, notificationBuilder.build())
        }


    }

    override fun updateLocationNotification() {
        TODO("Not yet implemented")
    }

    override fun removeLocationNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }


    companion object {
        const val CHANNEL_NAME_ID = "LOCATION_NOTIFY_CHANNEL"
        const val CHANNEL_NAME = "Location Channel"
        const val NOTIFICATION_ID = 1
    }

}

