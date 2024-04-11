package com.dqc.wanandroid.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import by.kirich1409.viewbindingdelegate.viewBinding
import com.dqc.base.presentation.activity.BaseActivity
import com.dqc.base.presentation.nav.NavManager
import com.dqc.wanandroid.R
import com.dqc.wanandroid.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host),
    NavController.OnDestinationChangedListener {
    private val binding: ActivityNavHostBinding by viewBinding()
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initAppBar() {
        binding.apply {

        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }
}