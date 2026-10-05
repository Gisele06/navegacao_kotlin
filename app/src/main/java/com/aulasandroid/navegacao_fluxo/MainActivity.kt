package com.aulasandroid.navegacao_fluxo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aulasandroid.navegacao_fluxo.screens.LoginScreen
import com.aulasandroid.navegacao_fluxo.screens.MenuScreen
import com.aulasandroid.navegacao_fluxo.screens.PedidosScreen
import com.aulasandroid.navegacao_fluxo.screens.PerfilScreen
import com.aulasandroid.navegacao_fluxo.ui.theme.Navegacao_FluxoTheme

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
                        startDestination = "login"
                    ){
                        composable (route = "login") {
                            LoginScreen(modifier = Modifier.padding(innerPadding))
                        }

                        composable (route = "menu") {
                            MenuScreen(modifier = Modifier.padding(innerPadding))
                        }

                        composable (route = "pedidos") {
                            PedidosScreen(modifier = Modifier.padding(innerPadding))
                        }

                        composable (route = "perfil") {
                            PerfilScreen(modifier = Modifier.padding(innerPadding))
                        }

                    }

                }
            }
        }
    }
}

