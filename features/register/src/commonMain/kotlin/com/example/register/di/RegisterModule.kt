package com.example.register.di

import com.example.navigation.Navigator
import com.example.register.presentation.FinishScreen
import com.example.register.presentation.LegalAddressScreen
import com.example.register.presentation.LegalNameScreen
import com.example.register.presentation.LegalResumeScreen
import com.example.register.presentation.RegisterScreen
import com.example.registerapi.FinishRoute
import com.example.registerapi.LegalAddressRoute
import com.example.registerapi.LegalNameRoute
import com.example.registerapi.LegalResumeRoute
import com.example.registerapi.RegisterRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation


@OptIn(KoinExperimentalAPI::class)
val registerModule = module {
    navigation<RegisterRoute> {
        val navigator: Navigator = get()
        RegisterScreen()
    }
    navigation<LegalNameRoute>{
        LegalNameScreen()
    }
    navigation<LegalAddressRoute>{
        LegalAddressScreen()
    }
    navigation<LegalResumeRoute>{
        LegalResumeScreen()
    }
    navigation<FinishRoute>{
        FinishScreen()
    }
}
