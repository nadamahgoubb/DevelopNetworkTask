package com.example.developnetworktask.data.source.remote


import com.example.developnetworktask.data.entity.ProductEntity
import com.example.developnetworktask.data.entity.UserModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {


    @GET("products")
    suspend fun getProducts()
      //  @Header("token ") token : String)
            : Response<ProductEntity>

    @POST("login")
    suspend fun login(
      @Field("phone") phone : String,
      @Field("pass") pass : String): Response<UserModel>

    @POST("register")
    suspend fun register(
        @Field("phone") phone : String,
        @Field("pass") pass : String,
        @Field("email") email : String)  : Response<UserModel>
}
