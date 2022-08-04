package com.example.developnetworktask.data.soure

import com.example.developnetworktask.domain.IAuthDataSource
import com.example.developnetworktask.domain.IAuthRepository
import com.example.developnetworktask.domain.IProductsDataSource
import com.example.developnetworktask.domain.IProductsRepository
import javax.inject.Inject


class DefaultRepo @Inject constructor(
    private val productsRemoteDataSource: IProductsDataSource,
    private val authRemoteDataSource: IAuthDataSource
) : IProductsRepository, IAuthRepository {

    override suspend fun getProduct() = productsRemoteDataSource.getProduct()

    override suspend fun login(phone: String, pass: String) =
        authRemoteDataSource.login(phone, pass)


    override suspend fun register(
        phone: String,
        pass: String,
        email: String
    ) = authRemoteDataSource.register(phone, pass, email)

}





