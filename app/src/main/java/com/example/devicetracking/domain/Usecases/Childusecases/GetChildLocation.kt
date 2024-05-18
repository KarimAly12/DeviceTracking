//package com.example.devicetracking.domain.Usecases.Childusecases
//
//import com.example.devicetracking.domain.model.ChildLocation
//import com.example.devicetracking.domain.repository.ChildRepository
//import com.google.firebase.database.ValueEventListener
//
//class GetChildLocation(private val repository: ChildRepository) {
//
//    suspend operator fun invoke(childID:String, valueEventListener: ValueEventListener): ChildLocation{
//        return repository.getChildLocation(childID, valueEventListener)
//    }
//}