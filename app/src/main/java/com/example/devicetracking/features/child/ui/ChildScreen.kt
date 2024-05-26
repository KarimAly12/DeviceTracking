package com.example.devicetracking.features.child.ui

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.amplifyframework.ui.authenticator.ui.AuthenticatorLoading
import com.example.devicetracking.core.tracking.service.DefaultLocationServiceManager
import com.example.devicetracking.core.domain.model.ChildObject
import com.example.devicetracking.core.tracking.service.CHILD_PARCEL
import com.example.devicetracking.core.tracking.service.LocationService
import com.example.devicetracking.core.ui.map.LocationMap
import com.example.devicetracking.core.ui.map.LocationUpdatesEffect
import com.example.devicetracking.core.ui.map.centerOnLocation
import com.example.devicetracking.core.ui.navigation.ScreenNavigationDrawer
import com.example.devicetracking.features.child.ui.navigation.ChildNavigation
import com.example.devicetracking.features.child.ui.navigation.ChildNavScreen
import com.example.devicetracking.ui.theme.colorButton1
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit



@Composable
fun ChildScreen(childViewModel: ChildViewModel= hiltViewModel()){


    if(childViewModel.child.value == ChildObject()){
        AuthenticatorLoading()
    }else{

        val screens = arrayOf(ChildNavScreen.Home, ChildNavScreen.Tracking, ChildNavScreen.Profile)
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val destination = navBackStackEntry?.destination
                    screens.forEach { screen->
                        BottomNavigationItem(
                            label = { Text(text = screen.name, color = Color.White, fontSize = 12.sp)},
                            icon = { Icon(painter = painterResource(id = screen.resourceId), contentDescription = null,tint=Color.White )},
                            selected = destination?.hierarchy?.any { screen.route == it.route } == true,
                            onClick = {
                                navController.navigate(screen.route){

                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true


                                }
                            }
                        )
                    }
                }
            }
        ) {innerPadding ->

            ChildNavigation(
                childViewModel = childViewModel,
                navHostController = navController,
                modifier = Modifier.padding(innerPadding) 
            )
        }

    }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildHome(childViewModel: ChildViewModel, navHostController: NavHostController){

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){



        Spacer(modifier = Modifier.weight(1f))


        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 250.dp),
            bitmap = childViewModel.bitmap.asImageBitmap(),
            contentDescription = null
        )

        
        Text(
            text = "Scan me to track me \uD83D\uDE01",
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.weight(1f))


    }
}



@Composable
fun ChildLocationMap(
    childViewModel: ChildViewModel,

){

    val context = LocalContext.current

    var locationRequest by remember { mutableStateOf<LocationRequest?>(null) }
    var locationLatLng by remember { mutableStateOf(LatLng(0.0,0.0)) }
    val cameraState = rememberCameraPositionState()

    if(childViewModel.child.value.inTrip){
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(3)).build()
    }else{
        locationRequest = null
    }

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
            modifier = Modifier

                .padding(it)
        ) {



            LocationMap(
                modifier = Modifier
                    .fillMaxSize(),
                locationLatLng = locationLatLng,
                cameraState)
        }

    }

}


@Composable
fun ChildProfile(
    childViewModel: ChildViewModel,
    navHostController: NavHostController
){

}
