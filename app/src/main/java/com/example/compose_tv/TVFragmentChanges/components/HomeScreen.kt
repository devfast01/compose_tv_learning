package com.example.compose_tv.TVFragmentChanges.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController


@Composable
fun FragmentsHomeScreen() {
    val navController = rememberNavController()
    Row {
        // TODO Left side of screen
        Box(
            Modifier
                .fillMaxSize()
                .weight(1.3f)
        ) {
            PreferencesNavigation(navController = navController)
        }

        // TODO Right side of screen
        Box(
            Modifier
                .fillMaxSize()
                .weight(0.7f)
        ) {
            RightMenu(
                modifier = Modifier.padding(end = 24.dp, top = 24.dp)
            )
        }
    }

}