package com.example.compose_tv.TVFragmentChanges.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.compose_tv.Gpt.components.GlassLeftMenu


@Composable
fun FragmentsHomeScreen() {
    val navController = rememberNavController()
    RightMenu(
        modifier = Modifier.padding(end = 24.dp, top = 24.dp)
    )
}