package com.example.devicetracking.presentation.ChildParentScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.domain.model.ChildLocationObject
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChildParentViewModel @Inject constructor(
    //private val childUsecases: ChildUsecases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val childID = checkNotNull(savedStateHandle["childID"])
    val child: MutableState<ChildObject> = mutableStateOf(ChildObject())

    var childLocation = mutableStateOf(ChildLocationObject())
    private val locationValueEventListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // Get updated value
            childLocation.value = dataSnapshot.getValue(ChildLocationObject::class.java)!!
            Log.i("testEventListener", childLocation.toString())

        }

        override fun onCancelled(error: DatabaseError) {


        }
    }


    var childLocationFlow: Flow<ChildLocationObject> =  flow {
        while (true){
            //val childLocation = childUsecases.getChildLocation(childID.toString(), locationValueEventListener)
            //emit(childLocation)
            delay(1000)
        }

    }

    init {
        getChildLocation()
    }

    fun getChildLocation(){

        viewModelScope.launch {
            //childUsecases.getChildLocation(childID.toString(), locationValueEventListener)

//             childLocationFlow.collect{location ->
//
//                 Log.i("testchildparentscreen" , location.toString())
//
//             }

        }
    }

}