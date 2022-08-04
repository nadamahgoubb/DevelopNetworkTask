package com.example.developnetworktask.ui.activity.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.developnetworktask.R
import com.example.developnetworktask.databinding.ActivityRegisterBinding
import com.example.developnetworktask.ui.activity.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        initView()

    }

    private fun initView() {
        binding.btnSignup.setOnClickListener(this)
        binding.txtSignin.setOnClickListener(this)
        binding.back.imageView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            (R.id.btn_signup) -> {
                //   checkInputs()

            }
            (R.id.txt_signin) -> {
                var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            }
            (R.id.back) -> {
                var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
            }

        }
    }
}