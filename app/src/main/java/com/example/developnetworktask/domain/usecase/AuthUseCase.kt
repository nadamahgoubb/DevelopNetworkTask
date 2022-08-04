package com.example.developnetworktask.domain.usecase

import com.example.developnetworktask.data.entity.UserModel
import com.example.developnetworktask.domain.IAuthRepository
import retrofit2.Response



suspend fun getLogin(authRepo: IAuthRepository, phone: String, pass: String)
: Response<UserModel>? = authRepo.login(phone, pass)






