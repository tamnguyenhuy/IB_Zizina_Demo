package demo.zizina.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import demo.zizina.features.login.LoginPage
import demo.zizina.features.main.HomeScreen
import demo.zizina.features.setup.DeviceSizeScreen
import demo.zizina.features.setup.DeviceTypeScreen
import demo.zizina.features.setup.SelectProviderScreen
import demo.zizina.features.setup.SelectProviderTypeScreen
import demo.zizina.features.setup.SetupPreviewScreen
import demo.zizina.features.setup.SetupXStreamProviderScreen
import demo.zizina.features.setup.viewmodel.SetupViewModel


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided")
}

@Composable
fun AppNavHost(navController: NavHostController) {
    val settingViewModel: SetupViewModel = viewModel()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController, startDestination = Setup.DeviceType.route) {
            composable(Auth.Login.route) {
                LoginPage(
                    onNavigateToSetup = { navController.navigate(Setup.DeviceType.route) }
                )
            }

            // page 1
            composable(Setup.DeviceType.route) {
                DeviceTypeScreen(
                    viewModel = settingViewModel,
                    onNext = { navController.navigate(Setup.DeviceSize.route) }
                )
            }

            // page 2
            composable(Setup.DeviceSize.route) {
                DeviceSizeScreen(
                    viewModel = settingViewModel,
                    onNext = { navController.navigate(Setup.SelectProvider.route) }
                )
            }

            // page 3
            composable(Setup.SelectProvider.route) {
                SelectProviderScreen(
                    viewModel = settingViewModel,
                    onNext = { navController.navigate(Setup.SelectProviderType.route) }
                )
            }

            // page 4
            composable(Setup.SelectProviderType.route) {
                SelectProviderTypeScreen(
                    viewModel = settingViewModel,
                    onNext = { navController.navigate(Setup.SetupXStreamProvider.route) }
                )
            }

            // page 5
            composable(Setup.SetupXStreamProvider.route) {
                SetupXStreamProviderScreen(
                    viewModel = settingViewModel,
                    onNext = { navController.navigate(Setup.SetupPreview.route) }
                )
            }

            // page 6
            composable(Setup.SetupPreview.route) {
                SetupPreviewScreen(
                    viewModel = settingViewModel,
                    onFinish = {
                        navController.navigate(Setup.DeviceType.route)
                    }
                )
            }

            // Guarded route (Main Home)
            composable(Main.Home.route) {
                HomeScreen()
            }
        }
    }
}

@Composable
fun NavHostController.canBack(): Boolean {
    return this.previousBackStackEntry != null
}