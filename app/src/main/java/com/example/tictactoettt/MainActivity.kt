package com.example.tictactoettt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoettt.data.GameViewModel
import com.example.tictactoettt.ui.theme.TicTacToeTTTTheme
import com.example.tictactoettt.uidata.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTTTTheme {
                val viewModel= viewModel<GameViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    App(viewModel,innerPadding)
                }
            }
        }
    }


    @Composable
    fun App(viewModel: GameViewModel, innerPadding: PaddingValues) {
        GameScreen(viewModel)
    }
}
