package com.example.raionapp.presentation.authentication

/**
 * Sealed class AuthState -> digunakan untuk menyimpan status autentikasi pengguna
 */
sealed class AuthState {
    object Unauthenticated : AuthState()
    object Authenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}