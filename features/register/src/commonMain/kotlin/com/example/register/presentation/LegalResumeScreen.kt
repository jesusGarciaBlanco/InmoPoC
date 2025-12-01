package com.example.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.event.Event
import com.example.navigationapi.event.RegisterEvent
import org.koin.compose.koinInject

@Composable
fun LegalResumeScreen(legalName: String, legalAddress: String,
) {
    val navEventController: NavEventController = koinInject()
    Column(
    modifier = Modifier.fillMaxSize().background(Color.Gray),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text("Legal Resume Screen")

        Text("LegalName = $legalName")
        Text("LegalAddress = $legalAddress")
        Button(onClick = {
            navEventController.sendEvent(RegisterEvent.OnRegistrationComplete)
        }){
            Text("Navigate Finish Screen")
        }
        Button(onClick = {
            navEventController.sendEvent(Event.OnBack)
        }){
            Text("go back")
        }
        Button(onClick = {
            navEventController.sendEvent(RegisterEvent.OnCloseRegisterFlow)
        }){
            Text("close flow")
        }
    }
}
