package com.example.mynewapp.util

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class showSnackbar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
