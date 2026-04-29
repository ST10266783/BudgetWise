package com.budgetwise.app.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.budgetwise.app.data.entities.User
import com.budgetwise.app.data.repository.UserRepository
import com.budgetwise.app.utils.isPasswordValid
import kotlinx.coroutines.launch

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {

    val loginResult   = MutableLiveData<AuthResult>()
    val registerResult = MutableLiveData<AuthResult>()

    fun login(identifier: String, password: String) {
        if (identifier.isBlank() || password.isBlank()) {
            loginResult.value = AuthResult.Error("Please fill in all fields.")
            return
        }
        viewModelScope.launch {
            val user = userRepository.login(identifier, password)
            loginResult.postValue(
                if (user != null) AuthResult.Success(user)
                else AuthResult.Error("Incorrect username/email or password.")
            )
        }
    }

    fun register(username: String, email: String, password: String, confirmPassword: String) {
        when {
            username.isBlank() || email.isBlank() || password.isBlank() ->
                registerResult.value = AuthResult.Error("Please fill in all fields.")
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                registerResult.value = AuthResult.Error("Enter a valid email address.")
            password != confirmPassword ->
                registerResult.value = AuthResult.Error("Passwords do not match.")
            !isPasswordValid(password) ->
                registerResult.value = AuthResult.Error(
                    "Password needs 8+ chars, one uppercase letter and one number."
                )
            else -> viewModelScope.launch {
                val result = userRepository.register(username, email, password)
                registerResult.postValue(
                    when (result) {
                        -1L -> AuthResult.Error("Username already taken.")
                        -2L -> AuthResult.Error("Email already registered.")
                        else -> AuthResult.Success(null)
                    }
                )
            }
        }
    }

    sealed class AuthResult {
        data class Success(val user: User?) : AuthResult()
        data class Error(val message: String) : AuthResult()
    }
}
