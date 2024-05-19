package com.example.devicetracking.domain.model


data class ChildLocationObject(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
data class ChildObject(
    var childID:String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    var childLocationObject: ChildLocationObject = ChildLocationObject(),
    val inTrip: Boolean = false
) {
    constructor() : this("","", "", "", ChildLocationObject(),false)
}