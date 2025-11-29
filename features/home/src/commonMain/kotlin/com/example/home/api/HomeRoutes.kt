package com.example.home.api


import com.example.navigation.Route
import com.example.resources.Res
import com.example.resources.home
import kotlinx.serialization.Serializable

@Serializable object HomeRoute : Route() {
    override val isTopLevel: Boolean = true
    override val requiresLogin: Boolean = true
    override val icon = Res.drawable.home
}
@Serializable object HomeDetailRoute : Route() {
    override val requiresLogin: Boolean = true
}
