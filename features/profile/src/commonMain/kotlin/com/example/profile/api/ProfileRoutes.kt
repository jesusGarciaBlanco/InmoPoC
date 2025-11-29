package com.example.profile.api

import androidx.navigation3.runtime.NavKey
import com.example.navigation.Navigator
import com.example.navigation.Route
import com.example.navigation.TopLevelNav
import com.example.resources.Res
import com.example.resources.face
import kotlinx.serialization.Serializable

@Serializable object ProfileRoute : Route() {
    override val isTopLevel: Boolean = true
    override val requiresLogin = true
    override val icon = Res.drawable.face
}
@Serializable object ProfileDetailRoute : Route() {
    override val requiresLogin = true
}
