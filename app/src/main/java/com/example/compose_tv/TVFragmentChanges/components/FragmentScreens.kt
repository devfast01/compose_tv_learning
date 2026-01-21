package com.example.compose_tv.TVFragmentChanges.components

sealed class FragmentScreens(val title: String) {
    object FragmentProfile : FragmentScreens("profile")
    object Permissions : FragmentScreens("permissions")
    object About : FragmentScreens("about")
    object Logout : FragmentScreens("logout")
}