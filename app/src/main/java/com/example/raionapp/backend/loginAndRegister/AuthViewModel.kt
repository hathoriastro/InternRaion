package com.example.raionapp.backend.loginAndRegister


import android.app.Application
import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException
import com.example.raionapp.R

/**
 *  AuthViewModel -> Turunan dari class AndroidViewModel dari Java.
 *  Digunakan untuk mengelolah proses autentikasi pengguna menggunakan firebase
 */
class AuthViewModel(
    application: Application,
) : AndroidViewModel(application) {

//    private val context: Context = application.applicationContext

    //Inisialisasi autentikasi firebase ke variabel auth
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    //    Inisialisasi _authState dengan MutableLiveData dari class AuthState
//    Berfungsi untuk menyimpan status autentikasi pengguna (telah login/signIn atau tidak)
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    //    Blok init yang berfungsi untuk langsung menjalankan fungsi checkAuthStatus
    init {
        checkAuthStatus()
    }

    /**
     * Memberi nilai Unauthenticated pada _authState jika pengguna belum login/signIn.
     * Memberi nilai Authenticated pada _authState jika pengguna telah login/signIn
     */
    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
        }
    }

    /**
     * Fungsi login -> digunakan untuk login pengguna
     */
    fun login(email: String, password: String) {

//        Memeriksa apakah email atau password yang dimasukkan ada atau tidak
        if (email.isEmpty() || password.isEmpty()) {
//            Jika ada yang kosong, maka akan diberi error dengan pesan
            _authState.value = AuthState.Error("Please fill in all fields")
            return
        }

//        Jika keduanya ada, maka login berjalan
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
//            Memeriksa apakah login berhasil atau tidak
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "An error occurred")
                }
            }
    }

    /**
     * Fungsi signIn -> digunakan untuk register pengguna
     */
    fun signIn(email: String, password: String) {

//        Memeriksa apakah email atau password yang dimasukkan ada atau tidak
        if (email.isEmpty() || password.isEmpty()) {
//            Jika ada yang kosong, maka akan diberi error dengan pesan
            _authState.value = AuthState.Error("Please fill in all fields")
            return
        }

//        Jika keduanya ada, maka register berjalan
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "An error occurred")
                }
            }
    }

    // All about google
    private val tag = "Google Authentication: "

    fun signInGoogle(activityContext: Context) {
        viewModelScope.launch {
            try {
                val result = buildCredentialRequest(activityContext)
                if (handleSignInGoogle(result) == true) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Unauthenticated
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
                if (e is CancellationException) throw e
                println(tag + "singIn error: ${e.message}")
                _authState.value = AuthState.Unauthenticated
            }
        }
    }

    private suspend fun handleSignInGoogle(result: GetCredentialResponse): Boolean {
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

    private suspend fun buildCredentialRequest(activityContext: Context): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setServerClientId(activityContext.getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return CredentialManager.create(activityContext).getCredential(
            request = request, context = activityContext
        )
    }
    /**
     * Fungsi signOut -> digunakan untuk logout pengguna
     */
    fun signOut(activityContext: Context) {
        viewModelScope.launch {
            CredentialManager
                .create(activityContext)
                .clearCredentialState(ClearCredentialStateRequest())
        }
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    // Percobaan pertama yang gagal total
//
//    fun signInWithGoogle() {
//        viewModelScope.launch {
//            try {
//                val googleIdOption = GetGoogleIdOption.Builder()
//                    .setServerClientId(getApplication<Application>().getString(R.string.web_client_id))
//                    .setFilterByAuthorizedAccounts(true)
//                    .build()
//
//                val request = GetCredentialRequest.Builder()
//                    .addCredentialOption(googleIdOption)
//                    .build()
//
//                val credentialManager = CredentialManager.create(getApplication())
//                val result = credentialManager.getCredential(getApplication(), request)
//                val credential = result.credential
//
//                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
//                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
//                    val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
//                    auth.signInWithCredential(firebaseCredential).addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            _authState.value = AuthState.Authenticated
//                        } else {
//                            _authState.value = AuthState.Error(task.exception?.message ?: "An error occurred")
//                        }
//                    }
//                } else {
//                    _authState.value = AuthState.Error("Invalid credential type")
//                }
//            } catch (e: Exception) {
//                _authState.value = AuthState.Error(e.localizedMessage ?: "An error occurred")
//            }
//        }
//    }
}