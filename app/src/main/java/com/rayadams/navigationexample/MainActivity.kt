package com.rayadams.navigationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavRoutes
import com.rayadams.navigationexample.navigation.NavigationParams
import com.rayadams.navigationexample.screens.MainScreen
import com.rayadams.navigationexample.screens.Screen1
import com.rayadams.navigationexample.screens.Screen2
import com.rayadams.navigationexample.screens.Screen3
import com.rayadams.navigationexample.screens.ScreenWithParameter
import com.rayadams.navigationexample.ui.theme.NavigationExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navHelper: CustomNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationExampleTheme {
                val navController: NavHostController = rememberNavController()

                LaunchedEffect(key1 = true) {
                    navHelper.navActions.collect { navigatorState ->
                        navigatorState?.let {
                            it.parcelableArguments.forEach { arg ->
                                navController.currentBackStackEntry?.arguments?.putParcelable(arg.key, arg.value)
                            }
                            navHelper.runNavigationCommand(it, navController)
                        }
                    }
                }
                Surface(modifier = Modifier
                    .fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoutes.MAIN_SCREEN
    ) {
        composable(NavRoutes.MAIN_SCREEN) {
            MainScreen()
        }
        composable(NavRoutes.SCREEN1) {
            Screen1()
        }
        composable(NavRoutes.SCREEN2) {
            Screen2()
        }
        composable(NavRoutes.SCREEN3) {
            Screen3()
        }
        composable(NavRoutes.SCREEN_WITH_PARAMETER_INSIDE,
            arguments = listOf(navArgument(NavigationParams.PARAMETER) { type = NavType.StringType })) {

            navController.currentBackStackEntry?.savedStateHandle?.set(
                NavigationParams.PARAMETER,
                navController.currentBackStackEntry?.arguments?.getString(NavigationParams.PARAMETER)
            )

            ScreenWithParameter()
        }
    }
}
