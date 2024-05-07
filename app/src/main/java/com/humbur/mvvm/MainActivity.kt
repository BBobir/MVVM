package com.humbur.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.humbur.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val maxUsernameLength = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,ApplicationViewModelFactory(application))[MainViewModel::class.java]

       binding.etUsername.addTextChangedListener{
           viewModel.setUsername(it.toString())
       }

        binding.etPassword.addTextChangedListener{
            viewModel.setPassword(it.toString())
        }

        binding.btnLogin.setOnClickListener {
            viewModel.onLoginCliked()
        }

        viewModel.usernameErrorMessage.observe(this){
        binding.txtUsernameError.text = it
        }

        viewModel.passwordErrorMessage.observe(this){
            binding.txtPasswordError.text =it
        }
        viewModel.usernameCounter.observe(this){
            if (it>maxUsernameLength){
                binding.txtUsernameCounter.setTextColor(ContextCompat.getColor(this,R.color.Maroon))
            }else{
                binding.txtUsernameCounter.setTextColor(ContextCompat.getColor(this,R.color.Navy))
            }
            binding.txtUsernameCounter.text = "$it/$maxUsernameLength"
        }
    }
}