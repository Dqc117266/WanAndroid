package com.dqc.wanandroid.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dqc.base.presentation.activity.BaseActivity
import com.dqc.base.presentation.ext.navigateSafe
import com.dqc.base.presentation.nav.NavManager
import com.dqc.home.presentation.screen.home.HomeFragment
import com.dqc.wanandroid.R
import com.dqc.wanandroid.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host),
    NavController.OnDestinationChangedListener {
    private val binding: ActivityNavHostBinding by viewBinding()
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAppBar()
        initNavManager()
        initBottomNavigation()
    }

    private fun initAppBar() {
        binding.apply {
            mainAppToolbar = binding.mainToolbar
            appBarLayout = binding.mainAppbarLayout
            searchTextInputEditText = binding.mainSearchTextInputEditText
            searchLayout = binding.mainSearchLayout
            searchTextInputLayout = binding.mainSearchTextInputLayout
        }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
            currentFragment?.navigateSafe(it)
        }
    }

    private fun initBottomNavigation() {
        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        navController.addOnDestinationChangedListener(this)
        binding.bottomNav.setupWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        when (destination.label) {
            DESTINATION_HOME_LABEL -> {
                HomeFragment.configureAppBar(this)
            }
            else -> {
                binding.mainAppbarLayout.visibility = View.GONE
            }
        }
    }
}