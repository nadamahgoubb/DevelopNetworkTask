package com.example.developnetworktask.ui.fragment.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.developnetworktask.data.entity.Product
import com.example.developnetworktask.data.entity.ProductEntity
import com.example.developnetworktask.domain.IProductsRepository
import com.example.developnetworktask.domain.usecase.showAllProducts
import com.example.developnetworktask.utils.NetworkConnectivity
import com.example.developnetworktask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var iProductsRepository: IProductsRepository,
) : ViewModel() {

    private lateinit var context: Context

    val _productsList: MutableLiveData<Resource<List<Product>>> =
        MutableLiveData(Resource.Loading())
    var productResponse: List<Product>? = null

    fun setActivity(context: Context) {
        this.context = context

    }

    fun getProducts(): MutableLiveData<Resource<List<Product>>> {
        _productsList.postValue(Resource.Loading())
        try {
            if (NetworkConnectivity.hasInternetConnection(context)) {
                viewModelScope.launch {
                    val response = //DataStoreManger().read(Constants.Token_KEY)?.let {
                        showAllProducts(iProductsRepository, "") //, it) }
                    response?.let { handleProductResponse(it) }
                        ?.let { _productsList.postValue(it) }
                }
            } else {
                _productsList.postValue(Resource.Error("No internet connection"))

            }

        } catch (e: Exception) {
            when (e) {
                is IOException -> _productsList.postValue(Resource.Error("Network Failure"))
                else -> _productsList.postValue(Resource.Error("Conversion Error"))
            }
        }


        return _productsList
    }

    private fun handleProductResponse(response: Response<ProductEntity>): Resource<List<Product>> {
        if (response.isSuccessful) {
            response.body().let { it ->
                if (productResponse == null) {
                    productResponse = it?.products

                }
                return productResponse?.let { it1 -> Resource.Success(it1) }!!
            }

        } else return Resource.Error(response.message())

    }
}
