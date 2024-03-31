package com.rayadams.navigationexample.screens.view_models

import androidx.lifecycle.ViewModel
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Screen2ViewModel @Inject constructor(private val customNavigator: CustomNavigator) :ViewModel() {
    fun navigateToMainScreen(){
        customNavigator.navigateAndClear(NavRoutes.MAIN_SCREEN)
    }
}