package com.everpath

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.everpath.ui.theme.EverpathTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val appContainer =
            (application as EverpathApplication)
                .appContainer

        lifecycleScope.launch {

            appContainer
                .syncManager
                .refresh()

        }

        setContent {
            EverpathTheme {
                App()
            }
        }
    }
}