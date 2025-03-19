package com.example.raionapp.presentation.register

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.raionapp.firestore.model.ProfileDataClass
import com.example.raionapp.firestore.ProfileCollection
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class AuthViewModel(
    application: Application,
) : AndroidViewModel(application) {

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val profileCollection = ProfileCollection()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    // Menggunakan StateFlow untuk userProfile, dideklarasikan sebagai nullable
    private val _userProfile = MutableStateFlow<ProfileDataClass?>(null)
    val userProfile: StateFlow<ProfileDataClass?> = _userProfile.asStateFlow()

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

    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Email dan password tidak boleh kosong")
            return
        }

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    loadUserProfile() // Mengambil data user dari Firestore
                } else {
                    _authState.value = AuthState.Error("Password atau email salah")
                }
            }
    }

    fun signIn(
        email: String,
        password: String,
        username: String,
        fullName: String,
    ) {
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || fullName.isEmpty()) {
            _authState.value = AuthState.Error("Isi semua yaww")
            return
        } // Mungkin nanti dapat dihilangkan

        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    saveUserProfile(username,fullName)
                } else {
                    _authState.value = AuthState.Error("Email sudah ada")
                }
            }
    }

    private fun saveUserProfile(
        username: String,
        fullName: String
    ) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            viewModelScope.launch {
                try {
                    val existingProfile = profileCollection.getProfileFromFirestore(currentUser.uid)

                    var role = "student"

                    if (existingProfile != null && existingProfile.role == "mentor") {
                        role = "mentor"
                    }

                    val profile = ProfileDataClass(
                        userId = currentUser.uid,
                        username = username,
                        fullname = fullName,
                        email = currentUser.email ?: "",
                        numberOfAnswer = 0,
                        numberOfQuestion = 0,
                        profilePicture = null,
                        role = role
                    )
                    profileCollection.addProfileToFirestore(profile)
                    Log.d("AuthViewModel", "Profile saved: ${profile.userId}")
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Error saving user profile: ${e.message}")
                }
            }
        }
    }


    private fun loadUserProfile() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            viewModelScope.launch {
                try {
                    val profile = profileCollection.getProfileFromFirestore(currentUser.uid)
                    if (profile != null) {
                        _userProfile.value = profile
                        Log.d("AuthViewModel", "Profile loaded: ${profile.userId}")
                    }
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Error loading user profile: ${e.message}")
                }
            }
        }
    }

//    Dibawah ini adalah semua tentang authentikasi menggunakan google
    private val tag = "Google Authentication: "
    fun signInGoogle(activityContext: Context) {
        _authState.value = AuthState.Loading
        viewModelScope.launch {
            try {
                val result = buildCredentialRequest(activityContext)
                if (handleSignInGoogle(result) == true) {
                    _authState.value = AuthState.Authenticated
                    saveUserProfileToGoogle()
                } else {
                    _authState.value = AuthState.Unauthenticated
                }
            } catch (e: Exception) {
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
                    .setServerClientId("913952271844-tk3acl0dfeb05nn9t7f6cstpr25fg6lu.apps.googleusercontent.com")
                    .setFilterByAuthorizedAccounts(false)
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        return CredentialManager.create(activityContext)
            .getCredential(
                request = request,
                context = activityContext
            )
    }

    private fun saveUserProfileToGoogle() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            viewModelScope.launch {
                try {
                    if (profileCollection.checkProfileExists(currentUser.uid)) {
                        loadUserProfile()
                    } else {
                        val profile = ProfileDataClass(
                            userId = currentUser.uid,
                            username = currentUser.displayName ?: "",
                            fullname = currentUser.displayName ?: "",
                            email = currentUser.email ?: "",
                            numberOfAnswer = 0,
                            numberOfQuestion = 0,
                            profilePicture = currentUser.photoUrl?.toString(),
                            role = "student"
                        )
                        profileCollection.addProfileToFirestore(profile)
                        Log.d("AuthViewModel", "Profile saved: ${profile.userId}")
                    }
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Error saving user profile: ${e.message}")
                }
            }
        }
    }

//    SignOut untuk seluruh authentikasi (google dan biasa)
    fun signOut(activityContext: Context) {
        auth.signOut()
        viewModelScope.launch {
            CredentialManager
                .create(activityContext)
                .clearCredentialState(ClearCredentialStateRequest())
        }
        _authState.value = AuthState.Unauthenticated
    }
}

