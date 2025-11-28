package com.example.inmopoc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.home.api.HomeRoute
import com.example.home.di.homeModule
import com.example.inmopoc.di.appModule
import com.example.inmopoc.theme.AppTheme
import com.example.login.di.loginModule
import com.example.navigation.Navigator
import com.example.navigation.TopLevelNav
import com.example.navigation.navigationModule
import com.example.profile.api.ProfileRoute
import com.example.profile.di.profileModule
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider

private val topLevelRoutes: List<NavKey> = listOf(HomeRoute, ProfileRoute)

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    KoinApplication(application = {
        val authModule = null
        modules(
            appModule,
            navigationModule,
            loginModule,
            homeModule,
            profileModule,
        )
    }) {
        val navigator: Navigator = koinInject()

        val currentScreen = navigator.backStack.lastOrNull()

        Scaffold(
            bottomBar = {
                if (currentScreen is TopLevelNav && navigator.isLoggedIn) {
                    NavigationBar {
                        topLevelRoutes.forEach { route ->
                            // Nos aseguramos de que la ruta sea una TopLevelNav para poder acceder a su icono.
                            if (route is TopLevelNav) {
                                NavigationBarItem(
                                    selected = currentScreen == route,
                                    // Al hacer clic, navegamos a la ruta. El Navigator se encarga de la lógica.
                                    onClick = { navigator.goTo(route) },
                                    icon = {
                                        Icon(
                                            imageVector = vectorResource(route.icon),
                                            contentDescription = route::class.simpleName
                                        )
                                    },
                                    label = { Text(route::class.simpleName ?: "") }
                                )
                            }
                        }
                    }
                }
            }
        ) { paddingValues ->
            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                backStack = navigator.backStack,
                onBack = { navigator.goBack() },
                entryProvider = koinEntryProvider()
            )
        }
    }
}
