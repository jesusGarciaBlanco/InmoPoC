package com.example.inmopoc.di


import com.example.home.api.HomeRoute
import com.example.login.api.LoginRoute
import com.example.navigation.NavigationState
import com.example.navigation.Navigator
import com.example.profile.api.ProfileRoute
import org.koin.dsl.module

val appModule = module {
    single {
        val topLevelRoutes = setOf(HomeRoute, ProfileRoute)

        NavigationState(
            startRoute = HomeRoute,
            topLevelRoutes = topLevelRoutes
        )
    }

    single {
        Navigator(
            state = get(),
            loginRoute = LoginRoute
        )
    }
}
