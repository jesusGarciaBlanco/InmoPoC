package com.example.register.di

import com.example.navigationapi.routes.RegisterRoutes
import com.example.register.presentation.FinishScreen
import com.example.register.presentation.LegalAddressScreen
import com.example.register.presentation.LegalNameScreen
import com.example.register.presentation.LegalResumeScreen
import com.example.register.presentation.RegisterScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation


@OptIn(KoinExperimentalAPI::class)
val registerModule = module {
    navigation<RegisterRoutes.RegisterHome> {
        RegisterScreen()
    }
    navigation<RegisterRoutes.LegalName>{
        LegalNameScreen()
    }
    navigation<RegisterRoutes.LegalAddress>{ route ->
        LegalAddressScreen(route.legalName)
    }
    navigation<RegisterRoutes.LegalResume>{ route ->
        LegalResumeScreen(
            legalName = route.legalName,
            legalAddress = route.legalAddress
        )
    }
    navigation<RegisterRoutes.Finish>{
        FinishScreen()
    }
}
