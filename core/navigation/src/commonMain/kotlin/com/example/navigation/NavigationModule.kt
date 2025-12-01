package com.example.navigation

import com.example.controller.NavEventControllerImpl
import com.example.coroutines.AppCoroutineScope
import com.example.navigationapi.controller.NavEventController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val navigationModule = module {
    single { AppCoroutineScope() }
    singleOf(::NavEventControllerImpl) bind NavEventController::class

}

