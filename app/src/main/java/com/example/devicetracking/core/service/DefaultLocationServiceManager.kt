package com.example.devicetracking.core.service

import android.content.Context
import android.content.Intent
import com.example.devicetracking.presentation.Location.LocationService
import dagger.hilt.android.qualifiers.ApplicationContext

class DefaultLocationServiceManager(
    @ApplicationContext private val context: Context
):LocationServiceManager {
    override fun startService() {
        val intent = Intent(context, LocationService::class.java)
        context.startService(intent)
    }

    override fun stopService() {
        val intent = Intent(context, LocationService::class.java)
        context.stopService(intent)
    }
}