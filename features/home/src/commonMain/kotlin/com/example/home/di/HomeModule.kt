package com.example.home.di

import com.example.home.api.HomeDetailRoute
import com.example.home.api.HomeRoute
import com.example.home.presentation.HomeDetailScreen
import com.example.home.presentation.HomeScreen
import com.example.navigation.Navigator
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val homeModule = module {
    navigation<HomeRoute> {
        val navigator: Navigator = get()
        HomeScreen(
//            onGoToDetail = { navigator.navigate(HomeDetailRoute) },
//            onGoToProfile = { navigator.navigate(ProfileRoute) }
        )
    }

    navigation<HomeDetailRoute> {
        HomeDetailScreen()
    }
}
