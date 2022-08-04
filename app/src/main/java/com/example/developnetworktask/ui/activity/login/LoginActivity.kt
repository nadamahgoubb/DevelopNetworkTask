package com.example.developnetworktask.ui.activity.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.developnetworktask.R
import com.example.developnetworktask.data.DataStoreManger
import com.example.developnetworktask.data.entity.UserModelData
import com.example.developnetworktask.databinding.ActivityLoginBinding
import com.example.developnetworktask.ui.activity.main.MainActivity
import com.example.developnetworktask.ui.activity.register.RegisterActivity
import com.example.developnetworktask.util.Constants
import com.example.developnetworktask.utils.Extension
import com.example.developnetworktask.utils.Extension.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private val loginVm: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setVm()
        initView()

        decideNextScreen()
    }

    private fun decideNextScreen() {
        lifecycleScope.launch {
            if(DataStoreManger().readBoolean(Constants.IS_LOGGED) == true) loginSucess(null)
        }
    }

    private fun setVm() {
        with(binding) {
            loginVm.setActivity(this@LoginActivity)
        }
        binding.lifecycleOwner = this

    }

    private fun initView() {
        binding.btnLogin.setOnClickListener(this)
        binding.btnForgetPass.setOnClickListener(this)
        binding.txtSignup.setOnClickListener(this)

        loginVm?.userModel?.observe(this) { result ->
            if (result.status) loginSucess(result.data)
            else Toast.makeText(this, "An error occured: ${result.message}", Toast.LENGTH_LONG)
                .show()
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            (R.id.btn_login) -> {
                checkInputs()
            }
            (R.id.txt_signup) -> {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

            }
        }
    }

    private fun checkInputs() {

        if (Objects.requireNonNull(binding.etPhone).length() < 11) {
            showSnackBar(this.resources.getString(R.string.phone_error), this@LoginActivity)
        } else if (binding.etLoginPassword.text?.length!! < 8) {
            showSnackBar(this.resources.getString(R.string.password_error), this@LoginActivity)
        } else {
            var phone = binding.etPhone.text
            var password = binding.etLoginPassword.text

            showSnackBar(this.resources.getString(R.string.done), this@LoginActivity)
            // loginVm.login(phone.toString(), password.toString())
// no api call -> make fake login sucess
            loginSucess(null)
        }

    }

    private fun loginSucess(data: UserModelData?) {
        Extension.hideProgressBar(binding.progressBar)
        data?.let { loginVm.saveToken(it) }
      //not right place for save IsLogged but for the task
        lifecycleScope.launch {
            DataStoreManger().saveBoolean(Constants.IS_LOGGED, true)
        }
        var intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}