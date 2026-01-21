package com.example.compose_tv.TVFragmentChanges.components.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FragmentButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        tonalElevation = 1.dp
    ) {
        Text(text = text, Modifier.padding(horizontal = 12.dp, vertical = 4.dp))
    }
}