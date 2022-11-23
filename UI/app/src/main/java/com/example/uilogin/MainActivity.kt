package com.example.uilogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.uilogin.databinding.ActivityMainBinding
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    companion object {
        private const val USERNAME_DATA = "username"
        private const val USERNUM_DATA = "norekening"
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtRekening.setText(intent.getStringExtra(USERNUM_DATA))
        binding.txtUser.setText(intent.getStringExtra(USERNAME_DATA))

        if(binding.txtRekening.text.isNotBlank()){
            binding.tvNamaRek.visibility = View.VISIBLE
        } else {
            binding.tvNamaRek.visibility = View.INVISIBLE
        }


        binding.button.setOnClickListener {
            startActivity(
                Intent(this, MainRegis::class.java).apply {
                    putExtra("username",binding.textInput.editText?.text.toString())
                }
            )
        }
        binding.lupa.setOnClickListener {
            registerPage.launch(MainRegis.newIntent(this))

            startActivity(
                Intent(this, MainRegis::class.java).apply {
                    putExtra("username",binding.textInput.editText?.text.toString())
                }
            )

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

    private val registerPage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val intent = it.data ?: return@registerForActivityResult
            val userName = intent.getStringExtra("user_data")

            binding.textInput.editText?.setText(userName)
        }
    }

}
