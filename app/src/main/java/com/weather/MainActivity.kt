package com.weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather.ui.theme.WeatherTheme
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale
import androidx.core.content.edit
import com.weather.LocationDataClass.Coordinates


class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient : FusedLocationProviderClient

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Weather)
        super.onCreate(savedInstanceState)

        setContent {
            WeatherTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),
                    modifier = Modifier.background(Color.Black)
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(all = 5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val context = LocalContext.current
                        CircularProgressIndicator()
                        LocationPermissionGate(
                            onGranted = {
                                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@MainActivity)
                                fusedLocationClient.lastLocation
                                    .addOnSuccessListener { location ->
                                        if (location != null) {
                                            // it will be reuse in search activity
                                            val coordinates = Coordinates(location.longitude,location.latitude)
                                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                            intent.putExtra("longitude", coordinates.longitude.toString())
                                            intent.putExtra("latitude", coordinates.latitude.toString())
                                            intent.putExtra("address", "notAddress")
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Location unavailable. Turn on GPS and try again.",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Error 404: Not found\nCheck the internet and try again",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            },
                            onBlocked = {
                                Toast.makeText(this@MainActivity, "Please Turn on the GPS for better experience", Toast.LENGTH_SHORT).show()
                                startActivity(
                                    Intent(this@MainActivity, SearchActivity::class.java)
                                )
                                finish()
                            }
                        )
                    }
                }
            }
        }





    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionGate(
    onGranted: () -> Unit,
    onBlocked: () -> Unit
) {
    val context = LocalContext.current

    val permissionState = rememberPermissionState(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(permissionState.status) {

        val status = permissionState.status
        val askedBefore = context.hasAskedLocationPermission()

        when {
            status.isGranted -> {
                onGranted()
            }

            !askedBefore -> {
                context.markAskedLocationPermission()
                permissionState.launchPermissionRequest()
            }

            status.shouldShowRationale -> {
                permissionState.launchPermissionRequest()
            }

            else -> {
                onBlocked()
            }
        }
    }
}


fun Context.hasAskedLocationPermission(): Boolean =
    getSharedPreferences("perm", Context.MODE_PRIVATE)
        .getBoolean("asked_location", false)

fun Context.markAskedLocationPermission() {
    getSharedPreferences("perm", Context.MODE_PRIVATE)
        .edit {
            putBoolean("asked_location", true)
        }
}
