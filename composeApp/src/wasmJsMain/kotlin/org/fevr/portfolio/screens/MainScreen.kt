package org.fevr.portfolio.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(){
    Column {
        OutlinedTextField("", onValueChange = {}, label = { Text("Prueba") })
        Text("Main Screen")
    }
}