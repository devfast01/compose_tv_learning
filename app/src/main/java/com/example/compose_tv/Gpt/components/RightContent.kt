package com.example.compose_tv.Gpt.components

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import com.example.compose_tv.Gpt.CategoryModel

@Composable
fun RightContent(
    modifier: Modifier = Modifier,
) {

    val categories = listOf(
        CategoryModel("Popular", List(8) { "Movie ${it + 1}" }),
        CategoryModel("Trending", listOf("Show A", "Show B", "Show C", "Show Q")),
        CategoryModel("Recommended", List(8) { "Film ${it + 1}" }),
        CategoryModel("Followings", List(8) { "Movie ${it + 1}" }),
    )

    val bannerFocusRequester = remember { FocusRequester() }
    val bannerBringIntoViewRequester = remember { BringIntoViewRequester() }

    val rowFocusRequesters = remember {
        List(categories.size) { FocusRequester() }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .focusGroup() // 🔒 stops left menu stealing focus
            .padding(end = 32.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(bottom = 48.dp)
    ) {

        // 🔥 HERO BANNER (ITEM 0)
        item {

            Box(
                modifier = Modifier
                    .bringIntoViewRequester(bannerBringIntoViewRequester)
                    .focusGroup()
            ) {
                HeroBanner(
                    modifier = Modifier
                        .focusRequester(bannerFocusRequester)
                        .bringIntoViewRequester(bannerBringIntoViewRequester),
                    bringIntoViewRequester = bannerBringIntoViewRequester,
                    onMoveDown = {
                        rowFocusRequesters.first().requestFocus()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // 📺 CATEGORY ROWS
        itemsIndexed(categories) { index, category ->

            Box(
                modifier = Modifier
                    .focusRequester(rowFocusRequesters[index])
                    .focusGroup() // 🔒 VERY IMPORTANT
            ) {
                CategoryRow(
                    category = category,
                    onMoveUp = {
                        if (index == 0) {
                            bannerFocusRequester.requestFocus()
                        } else {
                            rowFocusRequesters[index - 1].requestFocus()
                        }
                    },
                    onMoveDown = {
                        if (index < categories.lastIndex) {
                            rowFocusRequesters[index + 1].requestFocus()
                        }
                    }
                )
            }
        }
    }
}
