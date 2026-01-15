package com.example.compose_tv.Gpt.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_tv.R
import kotlinx.coroutines.coroutineScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import kotlinx.coroutines.launch


@Composable
fun HeroBanner(
    modifier: Modifier = Modifier,
    bringIntoViewRequester: BringIntoViewRequester,
    onMoveDown: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    var lastFocusedButtonIndex by rememberSaveable { mutableIntStateOf(0) }

    val buttonFocusRequesters = remember(2) {
        List(2) { FocusRequester() }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .focusGroup()
            .onFocusChanged {
                if (it.isFocused) {
                    // 🔥 restore LAST focused button
                    buttonFocusRequesters[lastFocusedButtonIndex].requestFocus()

                    // 🔥 ensure FULL visibility
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            }
            .height(280.dp)
            .clip(RoundedCornerShape(28.dp))
    ) {

        // 🎬 Background Image
        Image(
            painter = painterResource(id = R.drawable.tv_banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // 🌘 CINEMATIC GRADIENT (top → bottom + left)
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.85f),
                            Color.Black.copy(alpha = 0.55f),
                            Color.Transparent
                        )
                    )
                )
        )

        // 🎯 CONTENT BLOCK (Text + Buttons together)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 32.dp, bottom = 24.dp)
                .widthIn(max = 480.dp)
        ) {

            Text(
                text = "The Last Kingdom",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "A fearless warrior fights to reclaim his homeland in a world torn by war.",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 15.sp,
                maxLines = 3
            )

            Spacer(Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                HeroButton(
                    text = "Play",
                    modifier = Modifier
                        .focusRequester(buttonFocusRequesters[0])
                        .onPreviewKeyEvent {
                            if (it.key == Key.DirectionDown && it.type == KeyEventType.KeyDown) {
                                onMoveDown()
                                true
                            } else false
                        },
                    onFocused = {
                        lastFocusedButtonIndex = 0
                        println("Saved focus index = $lastFocusedButtonIndex")
                    }
                )

                HeroButton(
                    text = "More Info",
                    modifier = Modifier
                        .focusRequester(buttonFocusRequesters[1])
                        .onPreviewKeyEvent {
                            if (it.key == Key.DirectionDown && it.type == KeyEventType.KeyDown) {
                                onMoveDown()
                                true
                            } else false
                        },
                    onFocused = {
                        lastFocusedButtonIndex = 1
                        println("Saved focus index = $lastFocusedButtonIndex")
                    }
                )
            }
        }
    }
}


@Composable
fun HeroButton(text: String, modifier: Modifier = Modifier, onFocused: () -> Unit) {
    var isFocused by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.08f else 1f,
        label = "menu-scale"
    )

    Box(
        modifier = modifier
            .height(38.dp)
            .padding(horizontal = 10.dp)

            // 1️⃣ Focus logic
            .onFocusChanged {
                isFocused = it.isFocused
                if (it.isFocused) {
                    onFocused() // 🔥 bring banner fully into view
                }
            }
            .focusable()

            // TODO focus scale changes
//            // 2️⃣ Scale animation
//            .graphicsLayer {
//                scaleX = scale
//                scaleY = scale
//            }
//
//            // 3️⃣ Background
//            .background(
//                color = if (isFocused)
//                    Color(0xFFFF9505)
//                else
//                    Color.White,
//                shape = RoundedCornerShape(18.dp)
//            )
//            .background(
//                color = Color.DarkGray,
//                shape = RoundedCornerShape(21.dp)
//            )
            .background(
                color = if (isFocused)
                    Color.White
                else
                    Color.DarkGray,
                shape = RoundedCornerShape(18.dp),
            )

            .focusGlow(
                isFocused = isFocused,
                shape = RoundedCornerShape(21.dp)
            )
            // 4️⃣ Clip LAST
            .clip(RoundedCornerShape(18.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }

}

