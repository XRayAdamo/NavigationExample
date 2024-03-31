package com.rayadams.navigationexample.screens.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavigationParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenWithParameterViewModel @Inject constructor(
    state: SavedStateHandle,
    private val customNavigator: CustomNavigator
) : ViewModel() {

    var passedParameter by mutableStateOf("")
        private set

    init {
        passedParameter = state[NavigationParams.PARAMETER] ?: ""
    }

    fun goBack(){
        customNavigator.goBack()
    }
}