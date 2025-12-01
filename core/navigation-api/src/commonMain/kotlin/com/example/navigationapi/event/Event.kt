package com.example.navigationapi.event

import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.Route


sealed interface Event {

    fun toAction(): NavigationAction

    data object OnBack : Event {
        override fun toAction(): NavigationAction = NavigationAction.GoBack
    }

    data object OnLogout : Event {
        override fun toAction(): NavigationAction = NavigationAction.Logout
    }

    data object OnLogin : Event {
        override fun toAction(): NavigationAction = NavigationAction.Login
    }
}

