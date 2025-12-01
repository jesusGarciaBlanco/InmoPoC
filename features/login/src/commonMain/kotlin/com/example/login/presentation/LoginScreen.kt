package com.example.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.event.Event
import com.example.navigationapi.event.LoginEvent
import org.koin.compose.koinInject

@Composable
fun LoginScreen(
) {
    val navEventController: NavEventController = koinInject()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login Screen")
        Button(onClick = {
            navEventController.sendEvent(Event.OnLogin)
        }) {
            Text("Log In")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navEventController.sendEvent(LoginEvent.OnRegisterClick)
        }) {
            Text("Register")
        }
    }
}
