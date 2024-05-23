package com.example.devicetracking.core.tracking.service

import android.content.Context
import android.content.Intent
import dagger.hilt.android.qualifiers.ApplicationContext

class DefaultLocationServiceManager(
    @ApplicationContext private val context: Context,
    private val intent:Intent
): LocationServiceManager {
    override fun startService() {
        context.startService(intent)
    }

    override fun stopService() {
        context.stopService(intent)
    }
}