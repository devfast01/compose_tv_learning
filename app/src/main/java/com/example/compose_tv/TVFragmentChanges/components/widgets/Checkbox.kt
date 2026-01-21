package com.example.compose_tv.TVFragmentChanges.components.widgets

import android.R.attr.onClick
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.tv.material3.*


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    text: String,
    disabledText: String = text,
    onClick: (Boolean) -> Unit,
) {

    var isChecked by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    Row {
        Text(text = if (isChecked) disabledText else text)
        Surface(
            modifier = modifier
                .focusable()
                .onFocusChanged { isFocused = it.isFocused },
            shape = RoundedCornerShape(8.dp),

            onClick = {
                isChecked = !isChecked   // ✅ FIX HERE
                onClick(isChecked)
            },
            color = if (isFocused) Color(0xFF444444) else Color.DarkGray
        ) {
            Text(
                text = if (isChecked) "☑️" else "⬜",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}