package com.example.devicetracking.core.tracking.notification

import android.app.Notification

interface LocationNotification {

    var notificationBuilder: Notification.Builder
    fun createNotificationChannel()

    fun showLocationNotification()
    fun updateLocationNotification()

    fun removeLocationNotification()



}