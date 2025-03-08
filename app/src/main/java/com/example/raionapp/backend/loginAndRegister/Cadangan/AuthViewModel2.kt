package com.example.raionapp.backend.loginAndRegister.Cadangan


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.raionapp.backend.loginAndRegister.AuthState
import com.google.firebase.auth.FirebaseAuth

/**
 *  AuthViewModel -> Turunan dari class AndroidViewModel dari Java.
 *  Digunakan untuk mengelolah proses autentikasi pengguna menggunakan firebase
 */
class AuthViewModel2(application: Application) : AndroidViewModel(application) {

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

    /**
     * Fungsi signOut -> digunakan untuk logout pengguna
     */
    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}