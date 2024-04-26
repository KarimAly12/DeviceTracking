package com.example.devicetracking

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.devicetracking.presentation.Barcode.BarcodeScanning
import com.example.devicetracking.presentation.Location.LocationService
import com.example.devicetracking.presentation.Navigation.Navigation
import com.example.devicetracking.ui.theme.MyApplicationTheme
import com.example.devicetracking.presentation.requestPermissions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val barcodeScanning = BarcodeScanning(this)

        //val uploadWorkRequest:WorkRequest = OneTimeWorkRequestBuilder<UploadLocationWorker>().build()

//
//        val locationProvider = LocationProvider(this)
//        locationProvider.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        setContent {
            MyApplicationTheme {
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    requestPermissions(
                        onPermissionIsGranted = { /*TODO*/ },
                        onPermissionDenied = { /*TODO*/ },
                        onPermissionRevoked = {}
                    )


                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}