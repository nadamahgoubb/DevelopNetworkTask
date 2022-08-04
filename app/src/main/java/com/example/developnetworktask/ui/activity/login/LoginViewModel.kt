package com.example.developnetworktask.ui.activity.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.data.DataStoreManger
import com.example.developnetworktask.data.entity.UserModel
import com.example.developnetworktask.data.entity.UserModelData
import com.example.developnetworktask.domain.IAuthRepository
import com.example.developnetworktask.domain.usecase.getLogin
import com.example.developnetworktask.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var iAuthRepository: IAuthRepository) : ViewModel() {
    private lateinit var context: Context

    val _userModel = MutableLiveData<UserModel>()
    var userModel: LiveData<UserModel> = _userModel


    fun setActivity(context: Context) {
        this.context = context

    }

    fun login(phone: String, pass: String) = viewModelScope.launch {
        val response = getLogin(iAuthRepository, phone, pass)

        if (response?.isSuccessful() == true) {
            _userModel.value = response.body()
        } else {

        }
    }

    fun saveToken(data: UserModelData) {
        viewModelScope.launch {
            DataStoreManger().save(Constants.Token_KEY, data.token)
            DataStoreManger().saveBoolean(Constants.IS_LOGGED, data.IsLogged)
        }
    }

}