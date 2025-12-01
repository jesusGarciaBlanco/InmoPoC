package com.example.navigationapi.event

import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.RegisterRoutes
import com.example.navigationapi.routes.Route


object LoginEvent {
    data object OnRegisterClick : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(RegisterRoutes.RegisterHome)
    }
}
