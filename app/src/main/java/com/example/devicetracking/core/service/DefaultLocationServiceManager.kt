package com.example.devicetracking.core.service

import android.content.Context
import android.content.Intent
import com.example.devicetracking.presentation.Location.LocationService
import dagger.hilt.android.qualifiers.ApplicationContext

class DefaultLocationServiceManager(
    @ApplicationContext private val context: Context,
    private val intent:Intent
):LocationServiceManager {
    override fun startService() {
        context.startService(intent)
    }

    override fun stopService() {
        context.stopService(intent)
    }
}