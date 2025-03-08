package com.example.raionapp.backend.loginAndRegister.Cadangan

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.raionapp.backend.loginAndRegister.AuthState
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthClient(private val context: Context) {
    private val tag = "Google Authentication: "

    private val credentialManager = CredentialManager.create(context)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    suspend fun signInGoogle() {
        if (_authState.value == AuthState.Authenticated) {
            return
        }

        try {
            val result = buildCredentialRequest()
            if (handleSingInGoogle(result) == true) {
                _authState.value = AuthState.Authenticated
            } else {
                _authState.value = AuthState.Unauthenticated
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            println(tag + "sinIn error: ${e.message}")
            _authState.value = AuthState.Unauthenticated
        }
    }

    private suspend fun handleSingInGoogle(result: GetCredentialResponse): Boolean {
        val credential = result.credential

        if (
            credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {

                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println(tag + "name: ${tokenCredential.displayName}")
                println(tag + "email: ${tokenCredential.id}")
                println(tag + "image: ${tokenCredential.profilePictureUri}")

                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authResult = auth.signInWithCredential(authCredential).await()

                return authResult.user != null

            } catch (e: GoogleIdTokenParsingException) {
                println(tag + "GoogleIdTokenParsingException: ${e.message}")
                return false
            }

        } else {
            println(tag + "credential is not GoogleIdTokenCredential")
            return false
        }

    }

    private suspend fun buildCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("xxxxx")
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return credentialManager.getCredential(
            request = request, context = context
        )
    }
//
//    suspend fun signOut() {
//        credentialManager.clearCredentialState(ClearCredentialStateRequest())
//        auth.signOut()
//        _authState.value = AuthState.Unauthenticated
//    }
}