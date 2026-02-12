package com.example.compose_tv.LearningStateManagement

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.example.compose_tv.ui.theme.Compose_tvTheme

class StateManageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_tvTheme {
                // TODO click button to change focus
//                FocusRequesterDemo()
                // TODO manage focus directions
//                FocusableBoxGrid()

                // TODO key handling
                KeyHandlingDemo()
            }
        }
    }
}

@Composable
fun KeyHandlingDemo() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(200.dp)
            .focusable()
            .onKeyEvent { event ->
                if (event.type == KeyEventType.KeyDown) {

                    val message = when (event.key) {
                        Key.DirectionUp -> "Up pressed"
                        Key.DirectionDown -> "Down pressed"
                        Key.DirectionLeft -> "Left pressed"
                        Key.DirectionRight -> "Right pressed"
                        Key.DirectionCenter -> "Center pressed"
                        else -> null
                    }

                    message?.let {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        return@onKeyEvent true
                    }
                }
                false
            }
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text("Use D-Pad or Ok", color = Color.White)
    }

}


@Composable
fun FocusableBoxGrid() {
    val focusManager = LocalFocusManager.current

    val focusState = remember { List(size = 4) { mutableStateOf(false) } }
    val focusRequester = remember { List(size = 4) { FocusRequester() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEEEEEE))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            Row {
                FocusableColorBox(color = Color.Green, isFocused = focusState[0], focusRequester[0])
                Spacer(modifier = Modifier.width(16.dp))
                FocusableColorBox(color = Color.Blue, isFocused = focusState[1], focusRequester[1])
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                FocusableColorBox(
                    color = Color.Yellow,
                    isFocused = focusState[2],
                    focusRequester[2]
                )
                Spacer(modifier = Modifier.width(16.dp))
                FocusableColorBox(color = Color.Red, isFocused = focusState[3], focusRequester[3])
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { focusManager.clearFocus() }) { Text("Clear Focus") }
            Button(onClick = { focusManager.moveFocus(FocusDirection.Left) }) { Text("Left") }
            Button(onClick = { focusManager.moveFocus(FocusDirection.Right) }) { Text("Right") }
            Button(onClick = { focusManager.moveFocus(FocusDirection.Up) }) { Text("Up") }
            Button(onClick = { focusManager.moveFocus(FocusDirection.Down) }) { Text("Down") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { focusRequester[0].requestFocus() }) {
            Text("Reset Focus to Top-Left Box")
        }

//        LaunchedEffect(Unit) {
//            focusRequester[3].requestFocus()
//        }
    }
}

@Composable
fun FocusableColorBox(
    color: Color,
    isFocused: MutableState<Boolean>,
    focusRequester: FocusRequester,
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color)
            .border(
                width = if (isFocused.value) 4.dp else 2.dp,
                color = if (isFocused.value) Color.Red else Color.Gray
            )
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused.value = it.isFocused }
            .focusable()
    ) { }
}


@Composable
fun FocusRequesterDemo() {
    val requester1 = remember { FocusRequester() }
    val requester2 = remember { FocusRequester() }
    val requester3 = remember { FocusRequester() }

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        FocusBox(label = "Box 1", focusColor = Color.Red, focusRequester = requester1) {
            requester1.requestFocus()
        }

        FocusBox(label = "Box 2", focusColor = Color.Green, focusRequester = requester3) {
            requester2.requestFocus()
        }

        FocusBox(label = "Box 3", focusColor = Color.Blue, focusRequester = requester3) {
            requester3.requestFocus()
        }
    }

}


@Composable
fun FocusBox(
    label: String,
    focusColor: Color,
    focusRequester: FocusRequester,
    onClick: () -> Unit,
) {

    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(120.dp)
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable(false)
            .clickable { onClick() }
            .background(if (isFocused) focusColor else Color.DarkGray, RoundedCornerShape(12.dp))
            .border(width = 12.dp, color = if (isFocused) Color.Blue else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = Color.White)
    }
}