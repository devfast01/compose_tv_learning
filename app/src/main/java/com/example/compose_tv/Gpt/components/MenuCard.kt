package com.example.compose_tv.Gpt.components

import android.R.attr.scaleX
import android.R.attr.scaleY
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuCard(icon: ImageVector, title: String) {

    var isFocused by remember { mutableStateOf(false) }

//    val scale by animateFloatAsState(
//        targetValue = if (isFocused) 1.08f else 1f,
//        label = "menu-scale"
//    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            // TODO focus scale changes
//            .graphicsLayer {
//                scaleX = scale
//                scaleY = scale
//            }
            // TODO focus glow (focus border)
            .focusGlow(
                isFocused = isFocused,
                shape = RoundedCornerShape(21.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable(),
        colors = CardDefaults.cardColors(
//            containerColor = if (isFocused)
//                Color(0xFFFF9505)
//            else
            Color.DarkGray
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}
