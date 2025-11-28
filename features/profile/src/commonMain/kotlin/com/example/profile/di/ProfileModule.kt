package com.example.profile.di


import com.example.navigation.Navigator
import com.example.profile.api.ProfileDetailRoute
import com.example.profile.api.ProfileRoute
import com.example.profile.presentation.ProfileDetailScreen
import com.example.profile.presentation.ProfileScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val profileModule = module {
    navigation<ProfileRoute> {
        val navigator: Navigator = get()
        ProfileScreen(
            onLogout = { navigator.logout() },
        )
    }

    navigation<ProfileDetailRoute> {
        val navigator: Navigator = get()
        ProfileDetailScreen(
            onLogout = { navigator.logout() },
        )
    }
}
