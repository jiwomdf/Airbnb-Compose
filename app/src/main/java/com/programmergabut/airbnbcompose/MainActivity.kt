package com.programmergabut.airbnbcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.programmergabut.airbnbcompose.ui.bottomnavigation.BottomNavigationUi
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationGraph
import com.programmergabut.airbnbcompose.ui.theme.AirbnbComposeTheme

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirbnbComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationUi(navController = navController)
        }
    ) {
        NavigationGraph(navController = navController)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirbnbComposeTheme {
        Content()
    }
}