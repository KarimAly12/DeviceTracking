package com.example.devicetracking.presentation.ChildParentScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetracking.domain.Usecases.Childusecases.ChildUsecases
import com.example.devicetracking.domain.model.Child
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChildParentViewModel @Inject constructor(
    private val childUsecases: ChildUsecases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val childID = checkNotNull(savedStateHandle["childID"])
    val child: MutableState<Child> = mutableStateOf(Child())


    fun getChild(){

        viewModelScope.launch {
            while (true){
                Log.i("testchildparentscreen" , "getChild")
                childUsecases.getChild(childID.toString(), child)
                delay(10000)

            }
        }
    }

}