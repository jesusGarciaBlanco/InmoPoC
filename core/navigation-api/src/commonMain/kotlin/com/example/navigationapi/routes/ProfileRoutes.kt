package com.example.navigationapi.routes

import com.example.navigationapi.marker.RequiresLogin
import com.example.navigationapi.marker.TopLevelNav
import com.example.resources.Res
import com.example.resources.face
import kotlinx.serialization.Serializable

object ProfileRoutes {

    @Serializable
    object Home : Route, RequiresLogin, TopLevelNav{
        override val icon = Res.drawable.face
    }

    @Serializable
    object Detail : Route, RequiresLogin

}
