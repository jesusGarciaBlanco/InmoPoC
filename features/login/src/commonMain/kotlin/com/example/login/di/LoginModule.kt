package com.example.login.di

import com.example.login.api.LoginRoute
import com.example.login.presentation.LoginFlow
import com.example.navigation.Navigator
import com.example.registerapi.RegisterRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation


@OptIn(KoinExperimentalAPI::class)
val loginModule = module {
    navigation<LoginRoute> {
        val navigator: Navigator = get()
        LoginFlow(
            onLoginSuccess = {navigator.login()},
            onRegisterClick = {navigator.navigateTo(RegisterRoute)},
        )
    }
}
