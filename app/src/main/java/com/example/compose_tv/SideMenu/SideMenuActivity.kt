package com.example.compose_tv.SideMenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.compose_tv.R
import com.example.compose_tv.TVFragmentChanges.components.FragmentsHomeScreen
import com.example.compose_tv.ui.theme.Compose_tvTheme

class SideMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_tvTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    val menuFocusRequester = remember { FocusRequester() }
    val contentFocusRequester = remember { FocusRequester() }

    Row(modifier = Modifier.fillMaxSize()) {

        TvLeftMenu(
            menuFocusRequester = menuFocusRequester,
            contentFocusRequester = contentFocusRequester,
            onItemSelected = {
                // handle menu click
            }
        )

        ContentArea(
            contentFocusRequester = contentFocusRequester,
            menuFocusRequester = menuFocusRequester
        )
    }
}


@Composable
fun TvLeftMenu(
    menuFocusRequester: FocusRequester,
    contentFocusRequester: FocusRequester,
    onItemSelected: (MenuItem) -> Unit,
) {
    var focusedIndex by remember { mutableStateOf(0) }
    var menuExpanded by remember { mutableStateOf(false) }

    val itemFocusRequesters = remember {
        List(menuItems.size) { FocusRequester() }
    }

    val menuWidth by animateDpAsState(
        if (menuExpanded) 260.dp else 80.dp,
        label = ""
    )

    // ✅ initial focus goes to FIRST ITEM (not container)
    LaunchedEffect(Unit) {
        itemFocusRequesters.first().requestFocus()
    }

    LazyColumn(
        modifier = Modifier
            .width(menuWidth)
            .fillMaxHeight()
            .focusGroup() // ✅ allows child traversal
            .focusRequester(menuFocusRequester) // used only as ENTRY POINT
            .focusProperties {
                right = contentFocusRequester // ➡ move to content
            }
            .onFocusChanged {
                menuExpanded = it.hasFocus // child focus expands menu
            }
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1A1A1A), Color(0xFF0F0F0F))
                )
            )
            .padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(menuItems) { index, item ->
            TvMenuItem(
                item = item,
                isFocused = focusedIndex == index,
                expanded = menuExpanded,
                focusRequester = itemFocusRequesters[index],
                onFocus = { focusedIndex = index },
                onClick = { onItemSelected(item) }
            )
        }
    }
}


@Composable
fun TvMenuItem(
    item: MenuItem,
    isFocused: Boolean,
    expanded: Boolean,
    focusRequester: FocusRequester,
    onFocus: () -> Unit,
    onClick: () -> Unit,
) {
    val background by animateColorAsState(
        if (isFocused) Color(0x33FFFFFF) else Color.Transparent,
        label = ""
    )

    val scale by animateFloatAsState(
        if (isFocused) 1.05f else 1f,
        label = ""
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) onFocus()
            }
            .focusable()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(background, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = Color.White,
            modifier = Modifier.size(22.dp)
        )

        // 🔥 Text appears only when expanded
        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandHorizontally(),
            exit = fadeOut() + shrinkHorizontally()
        ) {
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = if (isFocused) FontWeight.Bold else FontWeight.Normal,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun ContentArea(
    contentFocusRequester: FocusRequester,
    menuFocusRequester: FocusRequester,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .focusRequester(contentFocusRequester)
            .focusProperties {
                left = menuFocusRequester // ⬅ BACK TO MENU
            }
            .focusable()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "CONTENT AREA",
            color = Color.White,
            fontSize = 28.sp
        )
    }
}

val menuItems = listOf(
    MenuItem("Home", Icons.Default.Home),
    MenuItem("Music", Icons.Default.ThumbUp),
    MenuItem("Videos", Icons.Default.Place),
    MenuItem("Favorites", Icons.Default.Favorite),
    MenuItem("Settings", Icons.Default.Settings),
)


data class MenuItem(
    val title: String,
    val icon: ImageVector,
)
