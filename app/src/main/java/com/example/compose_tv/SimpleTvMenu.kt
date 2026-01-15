package com.example.compose_tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_tv.ui.theme.Compose_tvTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

class SimpleTvMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_tvTheme {
                Surface(modifier = Modifier.fillMaxSize(), RectangleShape) {
                    AppNavHost()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Shared drawer state across all screens
    AppNavigationDrawer(
        drawerState = drawerState,
        navController = navController,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigationDrawer(
    drawerState: DrawerState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController, drawerState = drawerState)
        },
        modifier = modifier
    ) {
        // This is where your NavHost goes INSIDE the drawer
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                val scope = rememberCoroutineScope()
                HomeScreen(
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
            composable(Screen.Detail.route) {
                val scope = rememberCoroutineScope()
                DetailScreen(
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
            composable(Screen.Profile.route) {
                val scope = rememberCoroutineScope()
                ProfileScreen(
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()

    ModalDrawerSheet {
        // Drawer Header
        DrawerHeader()

        Spacer(Modifier.height(12.dp))

        // Drawer Items
        listOf(
            Screen.Home,
            Screen.Detail,
            Screen.Profile
        ).forEach { screen ->
            NavigationDrawerItem(
                label = { Text(screen.title) },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(screen.route) {
                        // Clear back stack when navigating from drawer
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "My App",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Your Screen Sealed Class (update with icons)
sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Detail : Screen("detail", "Details", Icons.Default.Info)
    object Profile : Screen("profile", "Profile", Icons.Default.Person)
}

// Updated Screens with Menu Button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMenuClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Home Screen Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onMenuClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Detail Screen Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onMenuClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                navigationIcon = {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Profile Screen Content")
        }
    }
}

//@Composable
//fun AppNavHost(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    NavHost(
//        navController = navController,
//        startDestination = Screen.Home.route,
//    ) {
//
//        composable(Screen.Home.route) {
//            AppNavigationDrawer {
//                HomeScreen()
//            }
//        }
//        composable(Screen.Detail.route) {
//            AppNavigationDrawer {
//                DetailScreen()
//            }
//        }
//        composable(Screen.Profile.route) {
//            AppNavigationDrawer {
//                ProfileScreen()
//            }
//        }
//    }
//}
//
//@Composable
//fun ProfileScreen() {
//    Text(text = "Profile")
//}
//
//@Composable
//fun DetailScreen() {
//    Text(text = "Detail")
//}
//
//@Composable
//fun HomeScreen() {
//    Text(text = "Home")
//}

//@Composable
//fun AppNavigationDrawer(
//    drawerState: DrawerState, // You'll need a DrawerState parameter
//    modifier: Modifier = Modifier,
//    content: @Composable () -> Unit,
//) {
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet {
//                // Drawer header
//                Text(
//                    text = "Header",
//                    modifier = Modifier.padding(16.dp)
//                )
//
//                // Drawer items
//                NavigationDrawerItem(
//                    label = { Text("Item 1") },
//                    selected = false,
//                    onClick = { /* Handle click */ },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//
//                NavigationDrawerItem(
//                    label = { Text("Item 2") },
//                    selected = false,
//                    onClick = { /* Handle click */ },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//            }
//        },
//        modifier = modifier,
//        content = content
//    )
//}
//

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_tvTheme {
        Greeting("Android")
    }
}