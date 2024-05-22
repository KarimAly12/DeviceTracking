package com.example.devicetracking.presentation.ChildScreen

import com.example.devicetracking.domain.model.ChildObject

sealed class ChildOperationState {

    data class UpdateChild(val child:ChildObject) : ChildOperationState()


}