package com.example.developnetworktask.domain.usecase

import com.example.developnetworktask.data.entity.ProductEntity
import com.example.developnetworktask.data.entity.UserModel
import com.example.developnetworktask.domain.IAuthRepository
import com.example.developnetworktask.domain.IProductsRepository
import retrofit2.Response


suspend fun showAllProducts(
    productsRepo: IProductsRepository,
    token: String
): Response<ProductEntity>? =
    productsRepo.getProduct()






