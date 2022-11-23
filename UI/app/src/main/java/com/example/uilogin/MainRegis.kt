package com.example.uilogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.uilogin.databinding.ActivityMainBinding
import com.example.uilogin.databinding.ActivityMainRegisBinding

class MainRegis : AppCompatActivity() {
    companion object {
        private const val USERNAME_DATA = "username"
        private const val USERNUM_DATA = "norekening"
        fun newIntent(context: Context): Intent {
            return Intent(context, MainRegis::class.java).apply {
/*                putExtra(USERNAME_DATA, userName)
                putExtra(USERNUM_DATA, userNum)*/
            }
        }
    }


    private lateinit var binding: ActivityMainRegisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRegisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idPengguna.editText?.setText(intent.getStringExtra(USERNAME_DATA))

        binding.btnSelanjutnya.setOnClickListener {
            setResult(RESULT_OK, Intent().apply {
                putExtra("username", binding.idPengguna.editText?.text.toString())

            })
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    putExtra("norekening",binding.iNoRekening.editText?.text.toString())
                    putExtra("username",binding.idPengguna.editText?.text.toString())
                }
            )
            finish()
        }

        binding.iNoRekening.editText?.doOnTextChanged { _, _, _, _ ->
            validate()
        }
        binding.idPengguna.editText?.doOnTextChanged { _, _, _, _ ->
            validate()
        }
        binding.iKataSandi.editText?.doOnTextChanged { _, _, _, _ ->
            validate()
        }
        binding.tvKonfirmasi.editText?.doOnTextChanged { _, _, _, _ ->
            validate()
        }
    }

    private fun validate() {
        binding.btnSelanjutnya.isEnabled =
            binding.iNoRekening.editText?.text.toString().isNotBlank()
                    && binding.idPengguna.editText?.text.toString().isNotBlank()
                    && binding.iKataSandi.editText?.text.toString().isNotBlank()
                    && binding.tvKonfirmasi.editText?.text.toString().isNotBlank()

    }
}