package com.example.inmopoc.di


import com.example.navigation.NavigationState
import com.example.navigation.Navigator
import com.example.navigationapi.routes.HomeRoutes
import com.example.navigationapi.routes.LoginRoutes
import com.example.navigationapi.routes.ProfileRoutes
import org.koin.dsl.module

val appModule = module {
    single {
        val topLevelRoutes = setOf(HomeRoutes.Home, ProfileRoutes.Home)

        NavigationState(
            startRoute = HomeRoutes.Home,
            topLevelRoutes = topLevelRoutes
        )
    }

    single {
        Navigator(
            state = get(),
            loginRoute = LoginRoutes.Login
        )
    }
}
