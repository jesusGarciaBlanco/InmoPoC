package com.example.navigationapi.event

import com.example.navigationapi.controller.NavigationAction
import com.example.navigationapi.routes.LoginRoutes
import com.example.navigationapi.routes.RegisterRoutes


object RegisterEvent {
    data object OnStartRegisterProcessClick : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(RegisterRoutes.LegalName)
    }

    data class OnLegalAddressClick(val legalName: String) : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(RegisterRoutes.LegalAddress(legalName))
    }

    data class OnLegalResumeClick(val legalName: String, val legalAddress:String) : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateTo(RegisterRoutes.LegalResume(legalName, legalAddress))
    }

    data object OnCloseRegisterFlow : Event {
        override fun toAction(): NavigationAction = NavigationAction.GoBackTo(
            route = LoginRoutes.Login,
            inclusive = false
        )
    }

    data object OnRegistrationComplete : Event {
        override fun toAction(): NavigationAction = NavigationAction.NavigateToWithPopUp(
            route = RegisterRoutes.Finish,
            popUpTo = RegisterRoutes.RegisterHome,
            inclusive = true
        )
    }
}
