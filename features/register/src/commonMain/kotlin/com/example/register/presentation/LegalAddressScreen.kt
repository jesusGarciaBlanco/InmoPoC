package com.example.register.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.event.Event
import com.example.navigationapi.event.RegisterEvent
import org.koin.compose.koinInject

@Composable
fun LegalAddressScreen(legalName: String) {
    val navEventController: NavEventController = koinInject()
    var legalAddress by remember { mutableStateOf("") }
    Column(
    modifier = Modifier.fillMaxSize().background(Color.Cyan),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
    ) {
        Text("Legal Address Screen")

        TextField(
            value = legalAddress,
            onValueChange = {legalAddress = it},
            label = { Text("Legal Address") }
        )

        Button(onClick = {
            navEventController.sendEvent(RegisterEvent.OnLegalResumeClick(
                legalName = legalName,
                legalAddress = legalAddress))
        }){
            Text("Navigate to Legal Resume Screen")
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
