package com.example.mounjarotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MounjaroTrackerTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MounjaroTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFF6D28D9),
            secondary = Color(0xFFA855F7),
            background = Color(0xFF121212),
            surface = Color(0xFF1E1E1E)
        ),
        content = content
    )
}

sealed class Screen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Inicio : Screen("inicio", "Início", Icons.Default.Home)
    object Tomas : Screen("tomas", "Tomas", Icons.Default.MedicalServices)
    object Refeicoes : Screen("refeicoes", "Refeições", Icons.Default.Restaurant)
    object Peso : Screen("peso", "Peso", Icons.Default.MonitorWeight)
    object Mais : Screen("mais", "Mais", Icons.Default.Menu)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(Screen.Inicio, Screen.Tomas, Screen.Refeicoes, Screen.Peso, Screen.Mais)

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF6D28D9), Color(0xFFA855F7))
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "Mounjaro Tracker",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFFA855F7))
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Inicio.route) { PlaceholderScreen("Início - Painel Principal") }
            composable(Screen.Tomas.route) { PlaceholderScreen("Tomas - Injeções e Locais") }
            composable(Screen.Refeicoes.route) { PlaceholderScreen("Refeições - Registo e IA") }
            composable(Screen.Peso.route) { PlaceholderScreen("Peso - Evolução e Gráficos") }
            composable(Screen.Mais.route) { PlaceholderScreen("Mais - Definições e Backup") }
        }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}
