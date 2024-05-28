package com.example.devicetracking.features.parent.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.example.devicetracking.core.domain.model.ParentObject
import com.example.devicetracking.core.domain.repository.ParentChildRepository
import com.example.devicetracking.core.domain.repository.ParentRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParentViewModel @Inject constructor(
    private val parentRepository: ParentRepository,
    private val parentChildRepository: ParentChildRepository,
):ViewModel() {


    var parent: MutableState<ParentObject> = mutableStateOf(ParentObject())


    init {

        initParent()

    }


    fun addChild(childEmail:String){
        viewModelScope.launch {
            parentChildRepository.addParentChild(childEmail, parent.value.email)

        }

    }

    private fun initParent(){
        viewModelScope.launch {
            Amplify.Auth.fetchUserAttributes(
                {
                    parentRepository.getParent(it[0].value){p ->
                        parent.value = p

                        viewModelScope.launch {
                            parentChildRepository.getChildren(p.email)

                        }
                    }
                },
                {
                    Log.e("AuthQuickstart", "Failed to fetch user attributes", it)
                }
            )



       }

    }

}