package com.mkdev.foursquarecodechallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.mkdev.foursquarecodechallenge.R
import com.mkdev.foursquarecodechallenge.databinding.ActivityMainBinding
import com.mkdev.foursquarecodechallenge.extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var currentNavController: LiveData<NavController>? = null
    private var backPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appBarConfiguration = AppBarConfiguration(findNavController().graph)
    }

    private fun findNavController(): NavController {
        (supportFragmentManager.findFragmentById(R.id.navigationHostFragment) as NavHostFragment).also {
            return it.navController
        }
    }

    override fun onBackPressed() {
        if (findNavController().graph?.startDestination == findNavController().currentDestination?.id) {
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
        super.onDestroy()
        _binding = null
    }
}