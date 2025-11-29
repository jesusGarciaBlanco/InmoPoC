package com.example.inmopoc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.home.api.HomeRoute
import com.example.home.di.homeModule
import com.example.inmopoc.di.appModule
import com.example.inmopoc.theme.AppTheme
import com.example.login.api.LoginRoute
import com.example.login.di.loginModule
import com.example.navigation.Navigator
import com.example.navigation.Route
import com.example.navigation.TopLevelNav
import com.example.navigation.navigationModule
import com.example.profile.api.ProfileRoute
import com.example.profile.di.profileModule
import com.example.resources.Res
import com.example.resources.arrow_back
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

private val topLevelRoutes: List<Route> = listOf(HomeRoute, ProfileRoute)

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {}
) = AppTheme(onThemeChanged) {
    KoinApplication(application = {
        modules(
            appModule,
            navigationModule,
            loginModule,
            homeModule,
            profileModule,
        )
    }) {
        val navigator: Navigator = koinInject()

        LaunchedEffect(Unit) {
            navigator.login() // Llama a login() al iniciar la app
        }

        val backStack = navigator.combinedBackStack
        val currentScreen = backStack.lastOrNull()

        Scaffold(
            topBar = {
                if (currentScreen !is LoginRoute) {
                    TopAppBar(
                        navigationIcon = {
                            if (backStack.size > 1 && currentScreen !is TopLevelNav && navigator.isLoggedIn) {
                                IconButton(
                                    onClick = { navigator.goBack() }
                                ) {
                                    Icon(
                                        imageVector = vectorResource(resource = Res.drawable.arrow_back),
                                        contentDescription = null,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            }
                        },
                        title = { Text("InmoPoC") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            },
            bottomBar = {
                if ((currentScreen as Route).isTopLevel && navigator.isLoggedIn) {
                    NavigationBar {
                        topLevelRoutes.forEach { route ->
                            if (route.isTopLevel) {
                                NavigationBarItem(
                                    selected = currentScreen == route,
                                    onClick = { navigator.navigateTo(route) },
                                    icon = {
                                        route.icon ?.let {
                                            Icon(
                                                imageVector = vectorResource(it),
                                                contentDescription = route::class.simpleName
                                            )
                                        }
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
                backStack = backStack,
                onBack = { navigator.goBack() },
                entryProvider = koinEntryProvider()
            )
        }
    }
}
