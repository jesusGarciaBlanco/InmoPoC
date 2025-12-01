package com.example.home.di

import com.example.home.presentation.HomeDetailScreen
import com.example.home.presentation.HomeScreen
import com.example.navigationapi.routes.HomeRoutes
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {
    navigation<HomeRoutes.Home> {
        HomeScreen()
    }

    navigation<HomeRoutes.Detail> {
        HomeDetailScreen()
    }
}
