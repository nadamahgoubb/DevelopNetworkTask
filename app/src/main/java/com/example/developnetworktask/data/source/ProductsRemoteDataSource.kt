package com.example.developnetworktask.data.soure

import android.util.Log
import com.example.developnetworktask.data.entity.ProductEntity
import com.example.developnetworktask.data.entity.UserModel
import com.example.developnetworktask.data.source.remote.ApiInterface
import com.example.developnetworktask.domain.IAuthDataSource
import com.example.developnetworktask.domain.IProductsDataSource
import retrofit2.Response
import javax.inject.Inject


class ProductsRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : IProductsDataSource, IAuthDataSource {

    override suspend fun getProduct(): Response<ProductEntity>? {
        try {
            return apiInterface.getProducts()

        } catch (e: Exception) {
            Log.d("BOOK ", e.message.toString())
            return null
        }
    }

    override suspend fun login(phone: String, pass: String)
    : Response<UserModel> {
        return apiInterface.login(phone, pass)
    }

    override suspend fun register(
        phone: String,
        pass: String,
        email: String
    ): Response<UserModel>? {
        return apiInterface.register(phone, pass, email)
    }


}





