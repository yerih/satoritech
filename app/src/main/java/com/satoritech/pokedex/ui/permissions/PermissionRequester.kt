package com.satoritech.pokedex.ui.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat

@Composable
fun PermissionRequester(context: Context, onGPSDisabled: ()->Unit = {}, onLocationChanged: (Location)->Unit){
    val permLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {}

    LaunchedEffect(key1 = Unit){

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            permLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)

        }else{

            (context.getSystemService(Context.LOCATION_SERVICE) as LocationManager).requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0,
                10f,
                object : LocationListener{
                    override fun onLocationChanged(p0: Location) = onLocationChanged(p0)
                    override fun onProviderDisabled(provider: String) = onGPSDisabled()
                }
            )

        }
    }
}


