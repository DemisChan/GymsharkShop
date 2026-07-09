package com.demis.gymsharkshop.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demis.gymsharkshop.presentation.detail.ProductDetailScreen
import com.demis.gymsharkshop.presentation.list.ProductListScreen
import com.demis.gymsharkshop.presentation.splash.SplashScreen

@Composable
fun GymsharkNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Splash) {
        composable<Splash> {
            SplashScreen(
                onEnter = {
                    navController.navigate(ProductList) {
                        popUpTo<Splash> { inclusive = true }
                    }
                },
            )
        }
        composable<ProductList> {
            ProductListScreen(
                onProductClick = { id -> navController.navigate(ProductDetail(id)) },
            )
        }
        composable<ProductDetail> {
            ProductDetailScreen(onBack = navController::popBackStack)
        }
    }
}
