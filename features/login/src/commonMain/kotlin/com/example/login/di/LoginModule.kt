package com.example.login.di

import com.example.login.presentation.LoginScreen
import com.example.navigationapi.routes.LoginRoutes
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation


@OptIn(KoinExperimentalAPI::class)
val loginModule = module {
    navigation<LoginRoutes.Login> {
        LoginScreen()
    }
}
