package com.example.devicetracking

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.getSystemService
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.configuration.AmplifyOutputs
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DeviceTrackingApp:Application() {


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()


        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(AmplifyOutputs(R.raw.amplify_outputs), applicationContext)
            Log.i("Amplify","Initialized Amplify")
        } catch (error: AmplifyException) {
            println("Could not initialize Amplify: $error")
        }

    }

    

    private fun createNotificationChannel(){

        val channel = NotificationChannel(
            "LOCATION_NOTIFY", "location", NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager :NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }
}