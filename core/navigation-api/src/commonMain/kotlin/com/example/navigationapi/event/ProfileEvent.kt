package com.example.navigationapi.event

import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.ProfileRoutes
import com.example.navigationapi.routes.Route


object ProfileEvent {
    data object OnProfileDetailClick : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(ProfileRoutes.Detail)    }
}
