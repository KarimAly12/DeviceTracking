package com.example.devicetracking.core.tracking.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Icon
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.devicetracking.R
import com.example.devicetracking.core.tracking.LocServiceEvents
import com.example.devicetracking.core.tracking.TrackingBroadcastReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
):LocationNotification {


   private val stopLocServiceIntent:Intent = Intent(context, TrackingBroadcastReceiver::class.java).apply {
       action = LocServiceEvents.StopLocService.event
   }
    private val stopLocServicePendingIntent:PendingIntent = PendingIntent
        .getBroadcast(context, 0, stopLocServiceIntent, PendingIntent.FLAG_IMMUTABLE)

    private val stopTripAction:Notification.Action = Notification.Action.Builder(
       Icon.createWithResource(context, R.drawable.round_stop_24),
        "End Trip", stopLocServicePendingIntent
    ).build()


   override var notificationBuilder: Notification.Builder = Notification
       .Builder(context, CHANNEL_NAME_ID)
       .setContentTitle("Tracking")
       .setContentText("Child in trip")
       .setSmallIcon(R.drawable.walk)
       .setContentIntent(stopLocServicePendingIntent)
       .addAction(stopTripAction)
       .setOngoing(true)


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

