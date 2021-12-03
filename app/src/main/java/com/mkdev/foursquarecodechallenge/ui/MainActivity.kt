package com.mkdev.foursquarecodechallenge.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.mkdev.foursquarecodechallenge.R
import com.mkdev.foursquarecodechallenge.core.dialog.showDialog
import com.mkdev.foursquarecodechallenge.databinding.ActivityMainBinding
import com.mkdev.foursquarecodechallenge.extension.*
import com.mkdev.foursquarecodechallenge.utils.LifecycleBoundLocationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var backPressedOnce = false

    @Inject
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var timerLocation: CountDownTimer? = null
    private var locationDialog: AlertDialog? = null

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appBarConfiguration = AppBarConfiguration(findNavController().graph)
        setupActionBarWithNavController(findNavController())

        if (isLocationEnable()) {
            if (hasLocationPermission()) {
                enableLocation()
            } else {
                requestLocationPermission(PERMISSION_ACCESS_COARSE_LOCATION_ID)
            }
        } else {
            showLocationEnableRequest()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ACCESS_COARSE_LOCATION_ID) {
            if (grantResults.getOrNull(0) == PackageManager.PERMISSION_GRANTED) {
                enableLocation()
            } else {
                showSnackBar(binding.root, getString(R.string.location_permission_hint))
                requestLocationPermission(PERMISSION_ACCESS_COARSE_LOCATION_ID)
            }
        }
    }

    private fun showLocationEnableRequest() {
        locationDialog = showDialog(
            message = getString(R.string.enable_location),
            cancelable = false,
            textPositive = getString(R.string.ok_string),
            positiveListener = {
                if (isLocationEnable()) {
                    locationDialog?.dismiss()
                    if (hasLocationPermission()) {
                        enableLocation()
                    } else {
                        requestLocationPermission(PERMISSION_ACCESS_COARSE_LOCATION_ID)
                    }
                } else {
                    showSnackBar(binding.root, getString(R.string.no_location))
                    locationDialog?.show()
                }
            }
        )

        lifecycleScope.launch {
            delay(1500)
            openSettingPage()
        }
    }

    private fun enableLocation() {
        bindLocationManager()
        setupLocationChecker()
    }

    private fun bindLocationManager() {
        LifecycleBoundLocationManager(
            this,
            fusedLocationProviderClient,
            locationCallback
        )
    }

    private fun setupLocationChecker() {
        // send broadcast to check location every 2 minute
        timerLocation = object : CountDownTimer(60 * 2 * 1000, 1000) {
            override fun onFinish() {
                sendNewLocationCheckerBroadcast()
                timerLocation?.start()
            }

            override fun onTick(millisUntilFinished: Long) {

            }

        }.start()
    }

    private fun sendNewLocationCheckerBroadcast() {
        val intent = Intent(LOCATION_CHECKER_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun findNavController(): NavController {
        (supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment).also {
            return it.navController
        }
    }

    override fun onBackPressed() {
        if (findNavController().graph.startDestination == findNavController().currentDestination?.id) {
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            showSnackBar(binding.root, getString(R.string.app_exit_label))

            lifecycleScope.launch {
                delay(2000)
                backPressedOnce = false
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        _binding = null
        timerLocation?.cancel()
        super.onDestroy()
    }

    companion object {
        const val LOCATION_CHECKER_BROADCAST = "LOCATION_CHECKER_BROADCAST"
        private const val PERMISSION_ACCESS_COARSE_LOCATION_ID = 11
    }
}