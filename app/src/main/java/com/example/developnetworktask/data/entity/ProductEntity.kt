package com.example.developnetworktask.data.entity

data class ProductEntity(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)