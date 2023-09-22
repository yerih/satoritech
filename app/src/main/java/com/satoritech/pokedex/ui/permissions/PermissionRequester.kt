package com.satoritech.pokedex.ui.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat

@Composable
fun PermissionRequester(onGPSDisabled: ()->Unit = {}, getLocation: ()->Unit){
    val permLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {}
    val context = LocalContext.current

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
            getLocation()
        }
    }
}


