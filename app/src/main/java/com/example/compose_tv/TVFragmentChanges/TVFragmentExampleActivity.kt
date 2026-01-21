package com.example.compose_tv.TVFragmentChanges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose_tv.TVFragmentChanges.components.FragmentsHomeScreen
import com.example.compose_tv.ui.theme.Compose_tvTheme

class TVFragmentExampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_tvTheme {
                FragmentsHomeScreen()
            }
        }
    }
}