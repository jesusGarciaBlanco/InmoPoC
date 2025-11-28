package com.example.inmopoc.di


import com.example.home.api.HomeRoute
import com.example.login.api.LoginRoute
import com.example.navigation.Navigator
import org.koin.dsl.module

val appModule = module {
    single {
        Navigator(
            startDestination = HomeRoute,
            loginRoute = LoginRoute
        )
    }
}
