package org.fevr.portfolio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun app() {
    val colorScheme = CustomLightColorScheme

    MaterialTheme(colorScheme) {
        Scaffold(
            topBar = { topWebAppBar() }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                content = { OutlinedTextField("", onValueChange = {}, label = { Text("Prueba") }) })
        }
    }
}

@Composable
fun topWebAppBar() {
    TopAppBar(
        title = { Text("Portfolio") },
        navigationIcon = { Icon(Icons.Filled.Menu, null) }
    )
}