package com.vp.week7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vp.week7.navigation.AppNavHost
import com.vp.week7.ui.theme.Week7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week7Theme {
                AppNavHost()
            }
        }
    }
}
