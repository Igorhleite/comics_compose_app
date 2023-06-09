package com.ileitelabs.comicscomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ileitelabs.comicscomposeapp.view.CharactersBottomNav
import com.ileitelabs.comicscomposeapp.view.CollectionScreen
import com.ileitelabs.comicscomposeapp.view.LibraryScreen
import com.ileitelabs.comicscomposeapp.viewmodel.MarvelComicsViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String) {
    object Library : Destination(route = "library")
    object Collection : Destination(route = "collection")
    object CharacterDetail : Destination(route = "character/{characterId]") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MarvelComicsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {             // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                val navController = rememberNavController()
                CharactersScaffold(navController = navController, viewModel = viewModel)
            }
        }
    }
}

@Composable
private fun CharactersScaffold(navController: NavHostController, viewModel: MarvelComicsViewModel) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { CharactersBottomNav(navController = navController) }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.Library.route) {
            composable(Destination.Library.route) {
                LibraryScreen(
                    navController = navController,
                    vm = viewModel,
                    paddingValues = paddingValues
                )
            }
            composable(Destination.Collection.route) {
                CollectionScreen()
            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->

            }
        }

    }
}