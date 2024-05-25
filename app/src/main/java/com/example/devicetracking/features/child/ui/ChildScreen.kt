package com.example.devicetracking.features.child.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.amplifyframework.ui.authenticator.ui.AuthenticatorLoading
import com.example.devicetracking.core.tracking.service.DefaultLocationServiceManager
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.tracking.service.CHILD_PARCEL
import com.example.devicetracking.core.tracking.service.LocationService
import com.example.devicetracking.core.ui.map.LocationMap
import com.example.devicetracking.core.ui.map.LocationUpdatesEffect
import com.example.devicetracking.core.ui.map.centerOnLocation
import com.example.devicetracking.core.ui.navigation.ScreenNavigationDrawer
import com.example.devicetracking.ui.theme.colorButton1
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildScreen(childViewModel: ChildViewModel = hiltViewModel(), navHostController: NavHostController){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()



    if(childViewModel.child.value == ChildObject()){
        AuthenticatorLoading()

    }else{


        ScreenNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {


                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = 250.dp)
                        .padding(start = 8.dp, end = 8.dp),
                    bitmap = childViewModel.bitmap.asImageBitmap(),
                    contentDescription = "")

        }) {

            Scaffold(
                topBar = {
                    Row{
                        IconButton(onClick = {
                            scope.launch {
                                if(drawerState.isOpen) drawerState.close() else drawerState.open()
                            }
                        }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Menu")
                        }
                    }
                }
            ) {

                Column(
                    modifier = Modifier.padding(it)

                ){



                    Spacer(modifier = Modifier.weight(1f))

                    ChildLocationMap(childViewModel)

                    Spacer(modifier = Modifier.weight(1f))


                }
            }
        }
    }
}



@Composable
fun ChildLocationMap(
    childViewModel: ChildViewModel

){

    val context = LocalContext.current

    var locationRequest by remember { mutableStateOf<LocationRequest?>(null) }
    var locationLatLng by remember { mutableStateOf(LatLng(0.0,0.0)) }
    val cameraState = rememberCameraPositionState()
    if(childViewModel.child.value.inTrip){
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(3)).build() }

    if(locationRequest != null){
        LocationUpdatesEffect(locationRequest = locationRequest!!) {result ->
            for (location in result.locations){
                locationLatLng = LatLng(location.latitude, location.longitude)
            }

        }

    }


    LaunchedEffect(key1 = locationLatLng) {

        cameraState.centerOnLocation(locationLatLng, cameraState.position.zoom)
    }




    Scaffold(
        floatingActionButton = {

            ExtendedFloatingActionButton(
                containerColor = colorButton1,
                onClick = {

                    childViewModel.apply {

                        child.value.inTrip = !child.value.inTrip
                        onChildOperationStateChanged(
                            ChildOperationState.UpdateChild(
                                child.value
                            )
                        )

                    }




                    val locationServiceIntent = Intent(context, LocationService::class.java)
                    locationServiceIntent.putExtra(CHILD_PARCEL, childViewModel.child.value)

                    val defaultLocationSM = DefaultLocationServiceManager(context = context, locationServiceIntent)

                    locationRequest = if(locationRequest == null){

                        defaultLocationSM.startService()
                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(3)).build()

                    }else{
                        defaultLocationSM.stopService()
                        null
                    }


                }) {
                Text(
                    text = if(childViewModel.child.value.inTrip) "End Trip" else "Start Trip",
                    color = Color.White
                )

            }
        }
    ) {


        Column(
            modifier = Modifier.padding(it)
        ) {



            LocationMap(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .size(600.dp),
                locationLatLng = locationLatLng,
                cameraState)
        }

    }

}

