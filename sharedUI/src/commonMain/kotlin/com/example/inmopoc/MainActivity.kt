package com.example.inmopoc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.ui.NavDisplay
import com.example.home.di.homeModule
import com.example.inmopoc.di.appModule
import com.example.inmopoc.theme.AppTheme
import com.example.login.di.loginModule
import com.example.navigation.Navigator
import com.example.navigation.navigationModule
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.event.HomeEvent
import com.example.navigationapi.marker.TopLevelNav
import com.example.navigationapi.routes.HomeRoutes
import com.example.navigationapi.routes.LoginRoutes
import com.example.navigationapi.routes.ProfileRoutes
import com.example.navigationapi.routes.Route
import com.example.profile.di.profileModule
import com.example.register.di.registerModule
import com.example.resources.Res
import com.example.resources.arrow_back
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI

private val topLevelRoutes: List<Route> = listOf(HomeRoutes.Home, ProfileRoutes.Home)

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
            registerModule,
        )
    }) {
        val navigator: Navigator = koinInject()

        val backStack = navigator.combinedBackStack
        val currentScreen = backStack.lastOrNull()

        val navEventController: NavEventController = koinInject()
        LaunchedEffect(Unit) {
            navEventController.routeState.collect { action ->
                when (action) {
                    is NavigationAction.NavigateTo ->
                        navigator.navigateTo(action.route)

                    is NavigationAction.NavigateToWithPopUp ->
                        navigator.navigateTo(action.route, action.popUpTo, action.inclusive)

                    is NavigationAction.GoBackTo ->
                        navigator.goBackTo(action.route, action.inclusive)

                    is NavigationAction.GoBack ->
                        navigator.goBack()

                    is NavigationAction.Login ->
                        navigator.login()

                    is NavigationAction.Logout ->
                        navigator.logout()
                }
            }
        }

        Scaffold(
            topBar = {
                if (currentScreen !is LoginRoutes.Login) {
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
                if ((currentScreen as Route) is TopLevelNav && navigator.isLoggedIn) {
                    NavigationBar {
                        topLevelRoutes.forEach { route ->
                            if (route is TopLevelNav) {
                                NavigationBarItem(
                                    selected = currentScreen == route,
                                    onClick = {
                                        navEventController.sendEvent(HomeEvent.OnBottomNavItemClick(route))
                                    },
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
                backStack = backStack,
                onBack = { navigator.goBack() },
                entryProvider = koinEntryProvider()
            )
        }
    }
}
