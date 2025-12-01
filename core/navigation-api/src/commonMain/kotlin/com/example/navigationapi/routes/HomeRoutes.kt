package com.example.navigationapi.routes


import com.example.navigationapi.marker.RequiresLogin
import com.example.navigationapi.marker.TopLevelNav
import com.example.resources.Res
import com.example.resources.home
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource

object HomeRoutes {
    @Serializable
    object Home : Route, RequiresLogin, TopLevelNav {
        override val icon: DrawableResource
            get() = Res.drawable.home
    }

    @Serializable
    object Detail : Route, RequiresLogin
}
