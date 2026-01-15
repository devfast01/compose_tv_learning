package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_tv.Gpt.CategoryModel

@Composable
fun CategoryRow(
    category: CategoryModel,
    onMoveUp: (() -> Unit)? = null,
    onMoveDown: (() -> Unit)? = null,
) {

    // 🔐 Focus memory (index)
    var lastFocusedIndex by remember { mutableIntStateOf(0) }

    // 🎯 FocusRequesters for items
    val focusRequesters = remember(category.items.size) {
        List(category.items.size) { FocusRequester() }
    }


    Column(
        modifier = Modifier
            .focusGroup() // 🔒 CRITICAL
    ) {
        Text(
            text = category.title,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier
                .focusGroup() // 🔒 CRITICAL
                .onFocusChanged { state ->
                    // 🔁 Restore focus when row regains focus
                    if (state.hasFocus) {
                        focusRequesters[lastFocusedIndex].requestFocus()
                    }
                },
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            itemsIndexed(category.items) { index, item ->
                MovieCard(
                    title = item, modifier = Modifier
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged {
                            if (it.isFocused) {
                                lastFocusedIndex = index
                            }
                        }
                        .onPreviewKeyEvent { event ->
                            if (event.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false

                            when (event.key) {
                                Key.DirectionUp -> {
                                    onMoveUp?.invoke()
                                    true
                                }

                                Key.DirectionDown -> {
                                    onMoveDown?.invoke()
                                    true
                                }

                                else -> false
                            }
                        })
            }
        }
    }
}
