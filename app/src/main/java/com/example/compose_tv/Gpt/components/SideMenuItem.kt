package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun SideMenuItem(
    text: String,
    modifier: Modifier = Modifier,
    onFocused: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .onFocusChanged {
                isFocused = it.isFocused
                if (it.isFocused) onFocused()
            }
            .focusable()
            .focusGlow(
                isFocused = isFocused,
                shape = RoundedCornerShape(14.dp),
                glowColor = Color.White
            )
            .background(
                color = if (isFocused) Color.White else Color.Transparent,
                shape = RoundedCornerShape(14.dp)
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            color = if (isFocused) Color.Black else Color.White,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}
