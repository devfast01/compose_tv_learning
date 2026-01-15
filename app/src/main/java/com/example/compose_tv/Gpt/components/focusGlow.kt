package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
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


fun Modifier.animatedFocusGlow(
    isFocused: Boolean,
    shape: Shape,
    glowColor: Color = Color.White,
    maxGlow: Dp = 6.dp,
): Modifier = this.then(
    Modifier.drawBehind {
        if (!isFocused) return@drawBehind

        val strokeWidth = maxGlow.toPx()
        val outline = shape.createOutline(size, layoutDirection, this)

        drawOutline(
            outline = outline,
            color = glowColor.copy(alpha = 0.9f),
            style = Stroke(width = strokeWidth)
        )
    }
)
