package com.example.devicetracking.presentation.ChildScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.devicetracking.core.location.Location.LocationUtils
import com.example.devicetracking.presentation.LocationMap.LocationMap
import com.example.devicetracking.presentation.LocationMap.LocationUpdatesEffect
import com.example.devicetracking.presentation.LocationMap.centerOnLocation
import com.example.devicetracking.ui.theme.colorButton1
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.concurrent.TimeUnit


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildScreen(childViewModel:ChildViewModel = hiltViewModel()){

    val context = LocalContext.current


    if(childViewModel.child.value.email == ""){
        childViewModel.getChild()

    }

    Column {


        Scaffold(

        ) {

            Column(
                modifier = Modifier.padding(it)
            ){



                Spacer(modifier = Modifier.weight(1f))

                ChildLocationMap()

//
//            Image(
//                modifier = Modifier
//                    .size(400.dp)
//                    .padding(8.dp),
//                bitmap = childViewModel.bitmap.asImageBitmap(),
//                contentDescription = "")
//
//            Text(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .align(Alignment.CenterHorizontally),
//                text = "Scan to add child device",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.SemiBold
//            )

                Spacer(modifier = Modifier.weight(1f))


            }


        }




    }





}



@Composable
fun ChildLocationMap(

){

    var locationRequest by remember {
        mutableStateOf<LocationRequest?>(null)
    }

    var locationLatLng by remember {
        mutableStateOf(LatLng(0.0,0.0))
    }

    var locationUpdates by remember {
        mutableStateOf("")
    }

    val cameraState = rememberCameraPositionState()

    if(locationRequest != null){
        LocationUpdatesEffect(locationRequest = locationRequest!!) {result ->
            for (location in result.locations){

                locationLatLng = LatLng(location.latitude, location.longitude)
                locationUpdates = "- @lat: ${location.latitude}\n" +
                        "- @lng: ${location.longitude}\n"

            }

        }

    }


    LaunchedEffect(key1 = locationLatLng) {
        cameraState.centerOnLocation(locationLatLng)
    }




    Scaffold(
        floatingActionButton = {

            ExtendedFloatingActionButton(
                containerColor = colorButton1,
                onClick = {

                    locationRequest = if(locationRequest == null){

                        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.SECONDS.toMillis(3)).build()

                    }else{
                        null
                    }

                    //childViewModel.getChildLocation()
//                        val intent = Intent(context, LocationService::class.java)
//                        context.startForegroundService(intent)


                }) {
                Text(
                    text = "Start Trip",
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
                    .padding(8.dp)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .size(600.dp)

                ,
                locationLatLng = locationLatLng,
                cameraState)
        }

    }






}