package com.rayadams.navigationexample.navigation

object NavRoutes {
    const val MAIN_SCREEN = "MainScreen"
    const val SCREEN1 = "Screen1"
    const val SCREEN2 = "Screen2"
    const val SCREEN3 = "Screen3"
    const val SCREEN_WITH_PARAMETER = "ScreenWithParameter"
    const val SCREEN_WITH_PARAMETER_INSIDE = "ScreenWithParameter/{${NavigationParams.PARAMETER}}"

}

object NavigationParams {
    const val PARAMETER = "Parameter"
}