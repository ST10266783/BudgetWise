package com.budgetwise.app.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.budgetwise.app.data.db.AppDatabase
import com.budgetwise.app.data.repository.UserRepository
import com.budgetwise.app.databinding.ActivityLoginBinding
import com.budgetwise.app.ui.MainActivity
import com.budgetwise.app.utils.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var session: SessionManager

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
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        session = SessionManager(this)

        if (session.isLoggedIn()) {
            goToMain()
            return
        }

        binding.btnLogin.setOnClickListener {
            val id  = binding.etIdentifier.text.toString().trim()
            val pwd = binding.etPassword.text.toString()
            binding.progressBar.visibility = View.VISIBLE
            viewModel.login(id, pwd)
        }

        binding.tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.loginResult.observe(this) { result ->
            binding.progressBar.visibility = View.GONE
            when (result) {
                is AuthViewModel.AuthResult.Success -> {
                    result.user?.let {
                        session.saveSession(it.id, it.username)
                        goToMain()
                    }
                }
                is AuthViewModel.AuthResult.Error ->
                    Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
