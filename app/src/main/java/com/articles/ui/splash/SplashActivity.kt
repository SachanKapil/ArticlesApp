package com.articles.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.articles.R
import com.articles.base.BaseActivity
import com.articles.constants.AppConstants
import com.articles.databinding.ActivitySplashBinding
import com.articles.ui.home.HomeActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var splashViewModel: SplashViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObservers()
        showSplashScreen()
    }

    private fun showSplashScreen() {
        splashViewModel.showSplashScreen()
    }

    private fun initViewModel() {
        splashViewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    private fun initObservers() {
        splashViewModel.getSplashLiveData().observe(this, {
            when (it) {
                AppConstants.ScreenConstants.OPEN_HOME_SCREEN -> openHomeActivity()
            }
        })
    }

    private fun openHomeActivity() {
        startActivityWithEnterTransition(Intent(this, HomeActivity::class.java))
        finish()
    }
}