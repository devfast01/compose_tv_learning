package com.example.compose_tv.TVFragmentChanges.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.tv.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_tv.TVFragmentChanges.components.widgets.FragmentButton

@Composable
fun FragmentDeleteAccountScreen() {
    PreferencesContainer(preference = FragmentScreens.Logout) {
        Column {

            Text("Are you sure you want to delete your account ?", color = Color.Red.copy(0.7f))
            Spacer(modifier = Modifier.size(24.dp))
            Row {
                FragmentButton(text = "Delete") {

                }
                Spacer(modifier = Modifier.size(8.dp))
                FragmentButton(text = "Disable") {

                }
            }
        }
    }
}

@Preview
@Composable
private fun LogoutScreenPrev() {
    FragmentDeleteAccountScreen()
}