package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type


@Composable
fun SideMenu(
    onMoveRight: () -> Unit
) {
    val menuItems = listOf("Home", "Search", "Movies", "TV Shows", "Settings")

    var lastFocusedIndex by rememberSaveable { mutableIntStateOf(0) }
    val focusRequesters = remember {
        List(menuItems.size) { FocusRequester() }
    }

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .width(if (isExpanded) 240.dp else 96.dp)
            .fillMaxHeight()
            .background(Color.Black.copy(alpha = 0.6f))
            .focusGroup()
            .onFocusChanged {
                isExpanded = it.hasFocus
                if (it.hasFocus) {
                    focusRequesters[lastFocusedIndex].requestFocus()
                }
            }
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        menuItems.forEachIndexed { index, item ->
            SideMenuItem(
                text = item,
                modifier = Modifier
                    .focusRequester(focusRequesters[index])
                    .onPreviewKeyEvent {
                        if (it.type == KeyEventType.KeyDown) {
                            when (it.key) {
                                Key.DirectionRight -> {
                                    onMoveRight()
                                    true
                                }
                                else -> false
                            }
                        } else false
                    },
                onFocused = {
                    lastFocusedIndex = index
                }
            )
        }
    }
}
