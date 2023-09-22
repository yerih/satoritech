package com.satoritech.pokedex.data.repositories

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import javax.inject.Inject

interface LocationRepository{
    suspend fun getLocation(
        onLocationDisabled: ()->Unit = {},
        onLocationChanged:  ()->Unit
    )
}

class LocationRepositoryImpl @Inject constructor(
    private val locationManager: LocationManager
): LocationRepository {

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(
        onLocationDisabled: ()->Unit,
        onLocationChanged:  ()->Unit
        ) {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            10f,
            object : LocationListener {
                override fun onProviderEnabled(provider: String) {

                }
                override fun onLocationChanged(p0: Location) = onLocationChanged()
                override fun onProviderDisabled(provider: String) = onLocationDisabled()
            }
        )
    }

}