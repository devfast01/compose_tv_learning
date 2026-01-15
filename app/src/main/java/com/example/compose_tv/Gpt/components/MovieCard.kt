package com.example.compose_tv.Gpt.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MovieCard(title: String, modifier: Modifier = Modifier) {
    var isFocused by remember { mutableStateOf(false) }

    // TODO change scale of item when it focused
//    val scale by animateFloatAsState(
//        targetValue = if (isFocused) 1.1f else 1f,
//        label = "movie-scale"
//    )

    // TODO Animate glow size
    val glowSize by animateDpAsState(
        targetValue = if (isFocused) 6.dp else 0.dp,
        label = "glow-size"
    )
    val glowAlpha by animateFloatAsState(
        targetValue = if (isFocused) 1f else 0f,
        label = "glow-alpha"
    )

    Card(
        modifier = modifier
            .width(200.dp)
            .height(120.dp)
            // TODO change scale of item when it focused
//            .graphicsLayer {
//                scaleX = scale
//                scaleY = scale
//            }

            // TODO add Animated glow
//            .animatedFocusGlow(
//                isFocused = isFocused,
//                shape = RoundedCornerShape(18.dp),
//                glowColor = Color.White.copy(alpha = glowAlpha),
//                maxGlow = glowSize
//            )

            // TODO add focus glow, not animated
            .focusGlow(isFocused, RoundedCornerShape(12.dp))
            // 🔐 Focus memory issue
            .onFocusChanged { isFocused = it.isFocused }
            .focusable(),

        colors = CardDefaults.cardColors(
//            containerColor = if (isFocused)
//                Color(0xFFFF9505)
//            else
            Color.DarkGray
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
