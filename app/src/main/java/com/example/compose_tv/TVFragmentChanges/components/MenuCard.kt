package com.example.compose_tv.TVFragmentChanges.components

import android.R.attr.scaleX
import android.R.attr.scaleY
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.Log
import com.example.compose_tv.Gpt.components.focusGlow

@Composable
fun MenuCard(icon: ImageVector, title: String) {
    val context = LocalContext.current   // 👈 THIS is the context
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
//                shape = RoundedCornerShape(21.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null, // TV style (no ripple)
                onClick = {
                    Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
                }
            )
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
