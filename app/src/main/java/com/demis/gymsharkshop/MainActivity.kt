package com.demis.gymsharkshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.demis.gymsharkshop.presentation.navigation.GymsharkNavHost
import com.demis.gymsharkshop.presentation.theme.GymsharkShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymsharkShopTheme {
                GymsharkNavHost()
            }
        }
    }
}
