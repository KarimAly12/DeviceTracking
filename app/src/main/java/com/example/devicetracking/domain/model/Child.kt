package com.example.devicetracking.domain.model


data class Location(
    val latitude: Float = 0f,
    val longitude: Float = 0f
)
data class Child(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val location: Location = Location(),
    val inTrip: Boolean = false
) {
    constructor() : this("", "", "", Location(), false)
}