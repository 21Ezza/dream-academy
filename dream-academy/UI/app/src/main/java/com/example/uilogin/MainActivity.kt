package com.example.uilogin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.uilogin.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userNameTest: String get() = binding.textInput.editText?.text.toString()
    private val userPassword: String get() = binding.textPassword.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Toast.makeText(
                this, "Username :$userNameTest Password : $userPassword", Toast.LENGTH_LONG
            ).show()
        }

        binding.textInput.editText?.doOnTextChanged { _, _, _, _ ->
            validate()
        }
        binding.textPassword.editText?.doOnTextChanged { _, _, _, _ ->
            if(validatePassword) {
                validate()
                binding.textPassword.error = null
            } else {
                binding.textPassword.error = "Password must contain Uppercase, Lowercase, Numbers, Special Character & At least 8 Character"
            }
        }
    }
    private fun validate() {
        binding.button.isEnabled =
            binding.textInput.editText?.text.toString().isNotBlank()
                    && binding.textPassword.editText?.text.toString().isNotBlank()
                    && validatePassword

    }

        private val validatePassword: Boolean get() {

            val valid = binding.textPassword.editText!!.text.toString()
            val isValid = valid.contains("[a-z]".toRegex())
                    && valid.contains("[A-Z]".toRegex())
                    && valid.contains("(?=.*[!^*@#\$%^&+=])".toRegex())
                    && valid.contains("[0-9]".toRegex())
                    && valid.length >= 8

            return isValid
        }
    }
