package com.vp.week7.navigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object CardDetail : Routes("card_detail/{cardId}") {
        fun buatRoute(cardId: String): String = "card_detail/$cardId"
    }
}
