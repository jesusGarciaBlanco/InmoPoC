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
import org.koin.compose.koinInject


@Composable
fun LegalNameScreen() {
    val navigator: Navigator = koinInject()
    var legalName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Legal Name Screen")

        TextField(
            value = legalName,
            onValueChange = {legalName = it},
            label = { Text("Legal Name") }
        )

        Button(onClick = {
            navigator.navigateTo(LegalAddressRoute(legalName))
        }){
            Text("Navigate to Legal Address Screen")
        }

        Button(onClick = {
            navigator.goBack()
        }){
            Text("close")
        }
    }
}
