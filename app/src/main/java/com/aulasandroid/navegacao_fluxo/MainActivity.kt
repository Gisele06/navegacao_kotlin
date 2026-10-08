package com.aulasandroid.navegacao_fluxo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aulasandroid.navegacao_fluxo.screens.MenuScreen
import com.aulasandroid.navegacao_fluxo.screens.PedidosScreen
import com.aulasandroid.navegacao_fluxo.screens.PerfilScreen
import com.aulasandroid.navegacao_fluxo.ui.theme.Navegacao_FluxoTheme
import com.aulasandroid.navegacao_fluxo.screens.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navegacao_FluxoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        exitTransition = {
                            slideOutOfContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            )+ fadeOut(animationSpec = tween(1000))
                        },
                        enterTransition = {
                            slideIntoContainer(
                                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            )
                        }
                    ){
                        composable (route = "login") {
                            LoginScreen(navController, modifier = Modifier.padding(innerPadding))
                        }

                        composable (route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding), navController)
                        }

                        composable (route = "pedidos?numeroPedido={numeroPedido}",
                                    arguments = listOf(
                                        navArgument("numeroPedido") {
                                            defaultValue = "Sem pedido"
                                        }
                                    )

                            ) {
                            val numeroPedido = it.arguments?.getString("numeroPedido")

                            PedidosScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                numeroPedidos = numeroPedido!!
                            )
                        }

                        composable (route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument(name = "nome"){
                                    type = NavType.StringType
                                },
                                navArgument(name = "idade"){
                                    type = NavType.IntType
                                }

                            )
                        ) {

                            val nome = it.arguments?.getString("nome")
                            val idade = it.arguments?.getInt("idade")

                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController,
                                nome = nome!!,
                                idade = idade!!
                            )
                        }
                    }
                }
            }
        }
    }
}

