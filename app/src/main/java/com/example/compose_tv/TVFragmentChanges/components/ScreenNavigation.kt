package com.example.compose_tv.TVFragmentChanges.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PreferencesNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = FragmentScreens.FragmentProfile.title
    ) {
        // e.g will add auth routes here if when we will extend project
        composable(
            FragmentScreens.FragmentProfile.title
        ) {
            FragmentProfileScreen()
        }
        composable(
            FragmentScreens.Permissions.title
        ) {
//            PermissionsScreen()
        }
        composable(
            FragmentScreens.About.title
        ) {
//            AboutScreen()
        }
        composable(
            FragmentScreens.Logout.title
        ) {
//            DeleteAccountScreen()
        }
    }
}
