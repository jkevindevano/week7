package com.vp.week7.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vp.week7.ui.screen.CardDetailScreen
import com.vp.week7.ui.screen.PokeTrackerScreen
import com.vp.week7.viewmodel.PokeTrackerViewModel

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    viewModel: PokeTrackerViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            PokeTrackerScreen(
                viewModel = viewModel,
                onCardClick = { cardId ->
                    navController.navigate(Routes.CardDetail.buatRoute(cardId))
                }
            )
        }

        composable(
            route = Routes.CardDetail.route,
            arguments = listOf(navArgument("cardId") { type = NavType.StringType })
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getString("cardId") ?: ""
            val kartuDipilih = viewModel.ambilKartuBerdasarkanId(cardId)

            if (kartuDipilih != null) {
                CardDetailScreen(
                    kartu = kartuDipilih,
                    onKembali = { navController.popBackStack() }
                )
            }
        }
    }
}
