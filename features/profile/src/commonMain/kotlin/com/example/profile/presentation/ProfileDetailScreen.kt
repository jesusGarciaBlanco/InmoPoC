package com.example.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.navigationapi.controller.NavEventController
import com.example.navigationapi.event.Event
import org.koin.compose.koinInject

@Composable
fun ProfileDetailScreen() {
    val navEventController: NavEventController = koinInject()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .safeDrawingPadding()
            .clip(RoundedCornerShape(48.dp))
    ) {
        Text("Profile Detail Screen")
        Button(onClick = {
            navEventController.sendEvent(Event.OnLogout)
        } ) {
            Text("Logout")
        }
    }
}
