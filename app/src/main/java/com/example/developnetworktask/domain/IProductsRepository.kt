package com.example.developnetworktask.domain

import com.example.developnetworktask.data.entity.ProductEntity
import retrofit2.Response


interface IProductsRepository {

    suspend fun getProduct(): Response<ProductEntity>?

}

interface IProductsDataSource {

    suspend fun getProduct(): Response<ProductEntity>?

}