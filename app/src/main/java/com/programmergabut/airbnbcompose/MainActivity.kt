package com.programmergabut.airbnbcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.programmergabut.airbnbcompose.ui.bottomnavigation.BottomNavigationUi
import com.programmergabut.airbnbcompose.ui.bottomnavigation.NavigationGraph
import com.programmergabut.airbnbcompose.ui.bottomnavigation.listFullScreenMenu
import com.programmergabut.airbnbcompose.ui.theme.AirbnbComposeTheme
import com.programmergabut.airbnbcompose.ui.theme.Teal500

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
    val scaffoldState = rememberScaffoldState()
    val isHasBottomBar = !listFullScreenMenu.contains(currentRoute(navController))
    Scaffold(
        bottomBar = {
            if(isHasBottomBar){
                BottomNavigationUi(navController = navController)
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    backgroundColor = Teal500,
                    snackbarData = data,
                    actionColor = Color.White
                )
            }
        },
    ) { padding ->
        if(isHasBottomBar){
            Column(modifier = Modifier.padding(bottom = 58.dp)) {
                NavigationGraph(
                    navController = navController,
                    scaffoldState = scaffoldState
                )
            }
        } else {
            NavigationGraph(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AirbnbComposeTheme {
        Content()
    }
}