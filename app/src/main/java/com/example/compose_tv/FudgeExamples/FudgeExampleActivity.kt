package com.example.compose_tv.FudgeExamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dreamsoftware.fudge.component.menu.FudgeTvSideMenu
import com.example.compose_tv.Gpt.GptHomeScreen
import com.example.compose_tv.Gpt.components.MenuCard
import com.example.compose_tv.R

class FudgeExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FudgeTvSideMenu(
                isSideMenuExpended = true,
                backgroundContainerColor = Color(0xFFFF9505),
                onDismissSideMenu = { /*TODO*/ },
                content = { GlassLeftMenu() }
            )
        }
    }
}


@Composable
fun GlassLeftMenu(
    modifier: Modifier = Modifier,
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
        LeftMenuItems(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 16.dp)
        )
    }
}


@Composable
fun LeftMenuItems(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MenuCard(Icons.Default.Home, "Home")
        MenuCard(Icons.Default.Search, "Search")
        MenuCard(Icons.Default.MailOutline, "Movies")
        MenuCard(Icons.Default.Settings, "Settings")
    }
}