package com.example.devicetracking.features.child.ui

import com.example.devicetracking.core.domain.model.ChildObject

sealed class ChildOperationState {

    data class UpdateChild(val child: ChildObject) : ChildOperationState()


}