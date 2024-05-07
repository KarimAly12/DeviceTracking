package com.example.devicetracking.domain.model


data class Location(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
data class Child(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    var location: Location = Location(),
    val inTrip: Boolean = false
) {
    constructor() : this("", "", "", Location(), false)
}