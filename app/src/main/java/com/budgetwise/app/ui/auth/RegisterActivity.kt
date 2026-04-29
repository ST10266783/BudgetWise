package com.budgetwise.app.ui.auth

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.UserRepository
import com.budgetwise.app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: AuthViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val db   = AppDatabase.getDatabase(applicationContext)
                val repo = UserRepository(db.userDao(), db.categoryDao())
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(repo) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            viewModel.register(
                username        = binding.etUsername.text.toString().trim(),
                email           = binding.etEmail.text.toString().trim(),
                password        = binding.etPassword.text.toString(),
                confirmPassword = binding.etConfirmPassword.text.toString()
            )
        }

        binding.tvGoLogin.setOnClickListener { finish() }

        viewModel.registerResult.observe(this) { result ->
            when (result) {
                is AuthViewModel.AuthResult.Success -> {
                    Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_LONG).show()
                    finish()
                }
                is AuthViewModel.AuthResult.Error ->
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
