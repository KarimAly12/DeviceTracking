package com.example.devicetracking.presentation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun requestPermissions(
    onPermissionIsGranted:() ->Unit,
    onPermissionDenied:() -> Unit,
    onPermissionRevoked:() -> Unit
){

    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.POST_NOTIFICATIONS
        )
    )




    LaunchedEffect(key1 = permissionState){

        val allPermissionRevoked = permissionState.permissions.size == permissionState.revokedPermissions.size

        //Get the permissions that needs to be requested
        val permissionsToRequest = permissionState.permissions.filter {
            !it.status.isGranted
        }
        //If there is a permission to request launch the permission that needs to be requested
        if(permissionsToRequest.isNotEmpty() ) permissionState.launchMultiplePermissionRequest()

        if(allPermissionRevoked){
            onPermissionRevoked()
        }else{
            if(permissionState.allPermissionsGranted){
                onPermissionIsGranted()
            }else{
                onPermissionDenied()
            }
        }



    }


}