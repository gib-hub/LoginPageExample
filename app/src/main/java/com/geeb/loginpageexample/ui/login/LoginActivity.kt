package com.geeb.loginpageexample.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geeb.loginpageexample.data.preference.UserPreference
import com.geeb.loginpageexample.databinding.ActivityMainBinding
import com.geeb.loginpageexample.ui.homepage.HomeActivity
import com.geeb.loginpageexample.utils.Constants
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setClickListener()
    }

    private fun setClickListener(){
        binding.btnLoginAccount.setOnClickListener {
            if(isFormLoginValid()){
                checkLogin()
            }
        }
        binding.llActionSsoFb.setOnClickListener {
            FancyToast.makeText(
                this,
                "Login FB Success!",
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }

        binding.llActionSsoGmail.setOnClickListener {
            FancyToast.makeText(
                this,
                "Login Gmail Success!",
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            ).show()
        }
    }

    private fun isFormLoginValid() : Boolean{

        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var isFormValid = true

        //jika kosong error
        if (username.isEmpty()){
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = "Username should be filled"
        }else{
            binding.tilUsername.isErrorEnabled = false
        }

        if (password.isEmpty()){
            isFormValid = false
            binding.tilPassword.isErrorEnabled = true
            binding.tilPassword.error = "Password should be filled"
        }else{
            binding.tilPassword.isErrorEnabled = false
        }

        return isFormValid
    }


    private fun checkLogin(){
        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        if (username == Constants.DUMMY_USERNAME && password == Constants.DUMMY_PASSWORD){
            FancyToast.makeText(this,
                "Login Success!",
                FancyToast.LENGTH_LONG,
                FancyToast.SUCCESS,
                true
            )
            saveLoginData()
            navigateToHomePage()
        } else {
            FancyToast.makeText(this,
                "Login Error!",
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,
                true
            )
        }
    }

    private fun navigateToHomePage(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun saveLoginData(){
        UserPreference(this).isUserLoggedIn = true
    }
}