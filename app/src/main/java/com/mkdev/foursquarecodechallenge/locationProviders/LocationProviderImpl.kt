package com.mkdev.foursquarecodechallenge.locationProviders

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.mkdev.foursquarecodechallenge.extension.asDeferred
import com.mkdev.foursquarecodechallenge.utils.PermissionNotGrantedException
import com.mkdev.presentation.locationProviders.LocationProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class LocationProviderImpl @Inject constructor(
    private val location: FusedLocationProviderClient,
    @ApplicationContext private val context: Context
) : LocationProvider {

    override suspend fun isLocationChanged(lastLat: Double, lastLng: Double): Boolean {
        return try {
            hasDeviceLocationChanged(lastLat, lastLng)
        } catch (ex: PermissionNotGrantedException) {
            false
        }
    }

    override suspend fun getLocationLatLng(): String {
        return try {
            if (isGoogleApiAvailable()) {
                val deviceLocation = getDeviceLocationAsync().await() ?: return ""
                "${deviceLocation.latitude},${deviceLocation.longitude}"
            } else {
                ""
            }
        } catch (ex: PermissionNotGrantedException) {
            ""
        }
    }

    private suspend fun hasDeviceLocationChanged(lastLat: Double, lastLng: Double): Boolean {
        if (!isGoogleApiAvailable()) return false
        val deviceLocation = getDeviceLocationAsync().await() ?: return false
        return calculateDistance(
            deviceLocation.latitude,
            deviceLocation.longitude,
            lastLat,
            lastLng
        ) > 100
    }

    private fun calculateDistance(lat1: Double, lng1: Double, lat2: Double, lng2: Double): Float {
        val earthRadius = 6371000.0 //meters
        val dLat = Math.toRadians((lat2 - lat1))
        val dLng = Math.toRadians((lng2 - lng1))
        val a =
            sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(
                dLng / 2
            ) * sin(dLng / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return (earthRadius * c).toFloat()
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun getDeviceLocationAsync(): Deferred<Location?> {
        return if (isLocationPermissionGranted()) {
            try {
                location.lastLocation.asDeferred()
            } catch (e: Exception) {
                throw PermissionNotGrantedException()
            }
        } else {
            throw PermissionNotGrantedException()
        }
    }

    private fun isGoogleApiAvailable(): Boolean {
        val googleResult =
            GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
        return googleResult == ConnectionResult.SUCCESS
    }
}