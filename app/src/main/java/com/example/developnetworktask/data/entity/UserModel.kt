package com.example.developnetworktask.data.entity

data class UserModel
    (
    val `data`: UserModelData,
    val message: String,
    val status: Boolean
)
data class UserModelData
    (
    val name: String,
    val phone: String,
    val email: String,
    val token: String,
    val IsLogged:Boolean
)