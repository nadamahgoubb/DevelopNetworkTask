package com.example.developnetworktask.ui.activity.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.developnetworktask.data.DataStoreManger
import com.example.developnetworktask.databinding.ActivitySplashBinding
import com.example.developnetworktask.ui.activity.main.MainActivity
import com.example.developnetworktask.util.Constants
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        Handler(Looper.getMainLooper()).postDelayed({
            decideNextActivity()
        }, 2000)
        setContentView(binding.root)
    }

    private fun decideNextActivity() {
        lifecycleScope.launch {
            if(DataStoreManger().readBoolean(Constants.IS_LOGGED) == true) {
                var intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()

            }
            else{
                var intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }
        }
    }

}