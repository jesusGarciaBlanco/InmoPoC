package com.example.profile.di

import com.example.navigationapi.routes.ProfileRoutes
import com.example.profile.presentation.ProfileDetailScreen
import com.example.profile.presentation.ProfileScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val profileModule = module {
    navigation<ProfileRoutes.Home> {
        ProfileScreen(
        )
    }

    navigation<ProfileRoutes.Detail> {
        ProfileDetailScreen()
    }
}
