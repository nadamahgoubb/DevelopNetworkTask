package com.example.developnetworktask.data.di


import com.example.developnetworktask.data.source.remote.ApiInterface
import com.example.developnetworktask.data.soure.DefaultRepo
import com.example.developnetworktask.data.soure.ProductsRemoteDataSource
import com.example.developnetworktask.domain.IAuthDataSource
import com.example.developnetworktask.domain.IAuthRepository
import com.example.developnetworktask.domain.IProductsDataSource
import com.example.developnetworktask.domain.IProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModul {

    @Singleton
    @Provides
    fun provideRemoteProductsDataSource(
        api: ApiInterface
    ): IProductsDataSource = ProductsRemoteDataSource(api)
    @Singleton
    @Provides
    fun provideRemoteAuthDataSource(
        api: ApiInterface
    ): IAuthDataSource = ProductsRemoteDataSource(api)

    @Singleton
    @Provides
    fun provideDefaultProductRepository(
        remoteProduct: IProductsDataSource,remoteAuth:IAuthDataSource):IProductsRepository  = DefaultRepo(remoteProduct,remoteAuth ) as IProductsRepository
    @Singleton
    @Provides
    fun provideDefaultIAuthRepository(
        remoteProduct: IProductsDataSource,remoteAuth:IAuthDataSource): IAuthRepository = DefaultRepo(remoteProduct ,remoteAuth) as IAuthRepository

}