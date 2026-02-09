package com.example.compose_tv.TVFragmentChanges.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun RightMenu(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(220.dp)
            .focusGroup() // 🔒 CRITICAL
            .clip(RoundedCornerShape(24.dp))
    ) {

        // 1️⃣ Blur ONLY what's behind
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(20.dp)
                .background(
                    Color.White.copy(alpha = 0.05f)
                )
        )

        // 2️⃣ Glass tint overlay
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Color.White.copy(alpha = 0.08f)
                )
                .border(
                    1.dp,
                    Color.White.copy(alpha = 0.18f),
                    RoundedCornerShape(24.dp)
                )
        )

        // 3️⃣ FOREGROUND content (NO BLUR)
        RightMenuItems(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp),
            navController
        )
    }
}


@Composable
fun RightMenuItems(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MenuCard(Icons.Default.Home, "Profile", navController)
        MenuCard(Icons.Default.Search, "Permissions", navController)
        MenuCard(Icons.Default.MailOutline, "About", navController)
        MenuCard(Icons.Default.Settings, "Delete Account", navController)
    }
}