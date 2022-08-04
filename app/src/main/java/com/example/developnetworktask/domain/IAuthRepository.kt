package com.example.developnetworktask.domain

import com.example.developnetworktask.data.entity.UserModel
import retrofit2.Response


interface IAuthRepository {

    suspend fun login(phone :String, pass:String): Response<UserModel>?
    suspend fun register( phone :String, pass:String,email: String): Response<UserModel>?
}

interface IAuthDataSource {

    suspend fun login(phone :String, pass:String): Response<UserModel>?
    suspend fun register( phone :String, pass:String,email: String): Response<UserModel>?

}