package com.example.compose_tv.Gpt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose_tv.Gpt.components.GlassLeftMenu
import com.example.compose_tv.Gpt.components.RightContent
import com.example.compose_tv.Gpt.components.SideMenu
import com.example.compose_tv.Gpt.components.TvBackgroundWithGradient
import com.example.compose_tv.R

@Composable
fun GptHomeScreen(
    onItemSelected: (String) -> Unit,
) {

    TvBackgroundWithGradient {

        val sideMenuFocusRequester = remember { FocusRequester() }
        val contentFocusRequester = remember { FocusRequester() }

        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // 📌 LEFT SIDE MENU
//            SideMenu(
//                modifier = Modifier
//                    .padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
//                    .focusRequester(sideMenuFocusRequester),
//                onMoveRight = {
//                    contentFocusRequester.requestFocus()
//                }
//            )

            // LEFT SIDE (Cards)
            GlassLeftMenu(
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, bottom = 24.dp)
            )

            Spacer(modifier = Modifier.width(24.dp))

            // RIGHT SIDE (Content)
            RightContent(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(contentFocusRequester),
            )

        }
    }
}







