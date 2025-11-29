package com.example.register.di

import com.example.navigation.Navigator
import com.example.register.presentation.RegisterScreen
import com.example.registerapi.RegisterRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation


@OptIn(KoinExperimentalAPI::class)
val registerModule = module {
    navigation<RegisterRoute> {
        val navigator: Navigator = get()
        RegisterScreen()
    }
}
