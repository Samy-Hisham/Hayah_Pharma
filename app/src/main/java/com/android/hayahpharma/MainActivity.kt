package com.android.hayahpharma

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.android.hayahpharma.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigationView()
        hideBottomWithSomeFragments()
    }

    private fun hideBottomWithSomeFragments() {
        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.homeFragment -> {
                    findViewById<BottomNavigationView>(R.id.bottomNavView).visibility =
                        View.VISIBLE
                }
                R.id.nearUserFragment -> {
                    findViewById<BottomNavigationView>(R.id.bottomNavView).visibility =
                        View.VISIBLE
                }
                R.id.expiredFragment2 -> {
                    findViewById<BottomNavigationView>(R.id.bottomNavView).visibility =
                        View.VISIBLE
                }
                else -> {
                    findViewById<BottomNavigationView>(R.id.bottomNavView).visibility =
                        View.GONE
                }
            }
        }
    }

    private fun setUpNavigationView() {

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        bottomNavView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}
