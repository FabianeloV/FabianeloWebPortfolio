package org.fevr.portfolio.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProjectsScreen(){
    Column {
        OutlinedTextField("", onValueChange = {}, label = { Text("Prueba") })
        Text("Project Screen")
    }
}