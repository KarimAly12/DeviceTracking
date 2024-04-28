package com.example.devicetracking.domain.Usecases.ParentUsecases

import com.example.devicetracking.domain.model.Parent

class ParentUsecases(
    val createParent: CreateParent,
    val addChildToParent: AddChildToParent,
    val getParent: GetParent,
    val getChildren: GetChildren
) {
}


