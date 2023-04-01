package com.example.sneakersapp.ui.home

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.sneakersapp.R
import com.example.sneakersapp.base.BaseActivity
import com.example.sneakersapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeVM, ActivityHomeBinding>() {

    private var bottomNavigationView: BottomNavigationView? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBottomSheet()
        initViews()
        onClickListener()
    }

    private fun setUpBottomSheet() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    private fun onClickListener() {

    }

    private fun initViews() {

    }

    override val layoutId: Int
        get() = R.layout.activity_home
}
