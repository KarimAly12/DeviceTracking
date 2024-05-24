package com.example.devicetracking.core.tracking

sealed class LocServiceEvents(val event:String) {
    object StopLocService:LocServiceEvents("stopLocationService")
}