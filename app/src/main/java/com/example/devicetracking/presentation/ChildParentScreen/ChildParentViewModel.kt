package com.example.devicetracking.presentation.ChildParentScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.model.Child
import com.example.devicetracking.domain.model.ChildLocation
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChildParentViewModel @Inject constructor(
    private val childUsecases: ChildUsecases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val childID = checkNotNull(savedStateHandle["childID"])
    val child: MutableState<Child> = mutableStateOf(Child())
    var childLocation: Flow<ChildLocation> =  flow {
        while (true){
            val childLocation = childUsecases.getChildLocation(childID.toString())
            emit(childLocation)
            delay(1000)
        }

    }

    init {
        getChild()
    }

    fun getChild(){

        viewModelScope.launch {


             childLocation.collect{location ->

                 Log.i("testchildparentscreen" , location.toString())

             }
//            while (true){
//                childUsecases.getChild(childID.toString(), child)
//                Log.i("testchildparentscreen" , child.value.inTrip.toString())
//                childLocation.value = child.value.location
//                delay(10000)
//
//            }
        }
    }

}