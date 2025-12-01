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
import com.example.navigation.Navigator
import com.example.registerapi.LegalAddressRoute
import com.example.registerapi.LegalResumeRoute
import com.example.registerapi.RegisterRoute
import org.koin.compose.koinInject

@Composable
fun LegalAddressScreen(legalName: String) {
    val navigator: Navigator = koinInject()
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
            navigator.navigateTo(LegalResumeRoute(
                legalName = legalName,
                legalAddress = legalAddress))
        }){
            Text("Navigate to Legal Resume Screen")
        }
        Button(onClick = {
            navigator.goBack()
        }){
            Text("go back")
        }
        Button(onClick = {
            navigator.goBackTo(RegisterRoute)
        }){
            Text("close flow")
        }
    }
}
