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
import com.example.navigation.Navigator
import com.example.profile.api.ProfileDetailRoute
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    val navigator: Navigator = koinInject()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .safeDrawingPadding()
            .clip(RoundedCornerShape(48.dp))
    ) {
        Text("Profile Screen")
        Text(
            "User is logged in: ${navigator.isLoggedIn}",
            color = if (navigator.isLoggedIn) Color.Green else Color.White
        )
        Button(onClick = {
            navigator.navigateTo(ProfileDetailRoute)
        }) {
            Text("Go to Profile Detail")
        }
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}
