package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.focusGlow(
    isFocused: Boolean,
    shape: Shape = RoundedCornerShape(16.dp),
    glowColor: Color = Color(0xFFFF9505),
): Modifier = this
    .then(
        if (isFocused) {
            Modifier
                .border(
                    width = 2.dp,
                    color = glowColor,
                    shape = shape
                )
                .shadow(
                    elevation = 16.dp,
                    shape = shape,
                    ambientColor = glowColor,
                    spotColor = glowColor
                )
        } else {
            Modifier
        }
    )
