package com.varol.testcase.internal.screen.main

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.varol.testcase.R
import com.varol.testcase.base.BaseActivity
import com.varol.testcase.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {
    override val layoutRes get() = R.layout.activity_main
    val navController: NavController by lazy { findNavController(R.id.navHostFragment) }

}

