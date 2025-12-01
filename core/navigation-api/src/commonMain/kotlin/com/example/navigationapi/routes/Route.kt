package com.example.navigationapi.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey {
    @Serializable
    data object Back : Route

    @Serializable
    data object Logout: Route

    @Serializable
    data object Login: Route
}
